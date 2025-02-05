package nosi.webapps.igrp.pages.session;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import nosi.core.config.Config;
import nosi.core.config.ConfigDBIGRP;
import nosi.core.webapp.Controller;
import nosi.core.webapp.Core;
import nosi.core.webapp.Response;
import nosi.webapps.igrp.dao.Application;

import static nosi.core.i18n.Translator.gt;

import java.io.File;
import java.io.FileInputStream;

public class SessionController extends Controller {		

	public Response actionIndex() throws IOException, IllegalArgumentException, IllegalAccessException{
		
		Session model = new Session();
		model.load();
		SessionView view = new SessionView();
		/*----#gen-example
		  EXAMPLES COPY/PASTE:
		  INFO: Core.query(null,... change 'null' to your db connection name added in application builder.
		model.loadTable_1(Core.query(null,"SELECT 'aplicacao_t' as aplicacao_t,'utilizadort' as utilizadort,'ip' as ip,'data_inicio_t' as data_inicio_t,'data_fim_t' as data_fim_t,'table_1_filter' as table_1_filter "));
		view.chart_t_sessao.loadQuery(Core.query(null,"SELECT 'X1' as EixoX, 15 as valor UNION SELECT 'X2' as EixoX,10 as valor UNION SELECT 'X2' as EixoX,23 as valor UNION SELECT 'X3' as EixoX,40 as valor"));
		view.chart_t_session_app.loadQuery(Core.query(null,"SELECT 'X1' as EixoX, 15 as valor UNION SELECT 'X2' as EixoX,10 as valor UNION SELECT 'X2' as EixoX,23 as valor UNION SELECT 'X3' as EixoX,40 as valor"));
		view.aplicacao.setQuery(Core.query(null,"SELECT 'id' as ID,'name' as NAME "));
		view.estado.setQuery(Core.query(null,"SELECT 'id' as ID,'name' as NAME "));
		  ----#gen-example */
		/*----#start-code(index)----*/
		
		view.aplicacao.setValue(new Application().getListApps()); 
		view.estado.setQuery(Core.query(null,"SELECT '0' as ID,'Inativo' as NAME union all SELECT '1' as ID,'Ativo' as NAME"));
		
//		Properties p = loadDbConfig("db", "db_igrp_config.xml");
//		String dbName = p.getProperty("type_db", "");
		String dbName = "postgresql";
		
		String sql1 = "SELECT 'X1' as EixoX, 15 as valor UNION SELECT 'X2' as EixoX,10 as valor UNION SELECT 'X2' as EixoX,23 as valor UNION SELECT 'X3' as EixoX,40 as valor";
		String sql2 = "SELECT 'X1' as EixoX, 15 as valor UNION SELECT 'X2' as EixoX,10 as valor UNION SELECT 'X2' as EixoX,23 as valor UNION SELECT 'X3' as EixoX,40 as valor";
		
		String startDate = model.getData_inicio(); 
		String endDate = model.getData_fim();
		
		if(startDate != null) {
			String[] aux = startDate.split("/");
			if(aux.length > 1) {
				startDate = aux[0].trim();
				endDate = aux[1].trim();
			}
		}
		
		switch(dbName) {
			case "oracle": 
				sql1 = "SELECT TO_CHAR (a.starttime, 'dd-mm-yyyy') as EixoX, COUNT (*) " + 
						"FROM tbl_session a " + 
						(Core.isNotNullMultiple(startDate,endDate)?
								"WHERE a.starttime BETWEEN TO_DATE ('" + startDate + "', 'dd-mm-yyyy') AND TO_DATE ('" + endDate + "', 'dd-mm-yyyy') ":"") + 
						"GROUP BY EixoX " + 
						"ORDER BY 1";
				
				sql2 = "SELECT b.dad || ' - ' || b.name applicao, TO_CHAR (a.starttime, 'dd-mm-yyyy hh24:mi') data_, COUNT (*) " + 
						"FROM tbl_session a, tbl_env b " + 
						(Core.isNotNullMultiple(startDate,endDate)?
								"WHERE b.id = a.env_fk AND a.starttime BETWEEN TO_DATE ('" + startDate + " 08', 'dd-mm-yyyy hh24') AND TO_DATE ('" + endDate + " 09', 'dd-mm-yyyy hh24') ":"") + 
						"GROUP BY applicao, data_ " + 
						"ORDER BY 1, 2";
			break;

			case "mysql","h2","postgresql": 
			default:
				sql1 = "SELECT TO_CHAR(TO_TIMESTAMP(starttime / 1000), 'DD/MM/YYYY') as EixoX, COUNT (*) as valor " + 
						"FROM tbl_session a "+
						(Core.isNotNullMultiple(startDate,endDate)?
							"WHERE TO_CHAR(TO_TIMESTAMP(starttime / 1000), 'DD/MM/YYYY') BETWEEN '" + startDate + "' AND '" + endDate + "' ":"")+
						"GROUP BY EixoX " + 
						"ORDER BY 1 ";
				
				sql2 = "SELECT b.dad || ' - ' || b.name applicao, TO_CHAR(TO_TIMESTAMP(starttime / 1000), 'DD/MM/YYYY') data_, COUNT (*) " + 
						"FROM tbl_session a, tbl_env b " + 
						(Core.isNotNullMultiple(startDate,endDate)?
								"WHERE b.id = a.env_fk AND TO_CHAR(TO_TIMESTAMP(starttime / 1000), 'DD/MM/YYYY') BETWEEN '" + startDate + "' AND '" + endDate + "' " :"")+ 
						"GROUP BY applicao, data_ " + 
						"ORDER BY 1, 2";
		}
		
		
		
		view.chart_t_sessao.loadQuery(Core.query(ConfigDBIGRP.FILE_NAME_HIBERNATE_IGRP_CONFIG, sql1));
		view.chart_t_session_app.loadQuery(Core.query(ConfigDBIGRP.FILE_NAME_HIBERNATE_IGRP_CONFIG, sql2));
		
		

		nosi.webapps.igrp.dao.Session session = new nosi.webapps.igrp.dao.Session();
		
		ArrayList<Session.Table_1> data = new ArrayList<>();
		List<nosi.webapps.igrp.dao.Session> sessions = new ArrayList<>();
		try {
			 sessions = session.find().andWhere("application", "=", model.getAplicacao()!=0?model.getAplicacao():null)
					 .andWhere("user.user_name", "=", model.getUtilizador())
					 .andWhere("user.status", "=", Core.toInt(model.getEstado()))
					 .all();
		}catch(Exception ignored) {
			
		}
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy"); 
		if(sessions!=null) {
			sessions =  sessions.stream().filter(obj -> {
				Date auxEndTime = new Date(obj.getEndTime());
				Date auxStartTime = new Date(obj.getStartTime());
				return !((model.getData_inicio() != null && !model.getData_inicio().isEmpty() && model.getData_inicio().compareTo(dateFormat.format(auxStartTime)) > 0)
						||
						(model.getData_fim() != null && !model.getData_fim().isEmpty() &&
						model.getData_fim().compareTo(dateFormat.format(auxEndTime)) < 1));
			}).toList();
			
			SimpleDateFormat auxFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			
			for(nosi.webapps.igrp.dao.Session s : sessions ){
				Session.Table_1 table = new Session.Table_1();			
				Date auxEndTime = new Date(s.getEndTime());
				Date auxStartTime = new Date(s.getStartTime());				
				table.setData_fim_t(""+auxFormat.format(auxEndTime));
				table.setData_inicio_t(""+auxFormat.format(auxStartTime));
				table.setAplicacao_t(""+s.getApplication().getDad());
				table.setIp(s.getIpAddress());
				table.setUtilizadort(s.getUserName());
				data.add(table);
			}
		}
		
		
		view.table_1.addData(data);		
		
		/*----#end-code----*/
		view.setModel(model);
		return this.renderView(view);	
	}
	
	public Response actionPesquisar() throws IOException, IllegalArgumentException, IllegalAccessException{
		
		Session model = new Session();
		model.load();
		/*----#gen-example
		  EXAMPLES COPY/PASTE:
		  INFO: Core.query(null,... change 'null' to your db connection name added in application builder.
		 this.addQueryString("p_id","12"); //to send a query string in the URL
		 return this.forward("igrp","Session","index", this.queryString()); //if submit, loads the values
		  ----#gen-example */
		/*----#start-code(pesquisar)----*/
		
		
		
		/*----#end-code----*/
		return this.redirect("igrp","Session","index", this.queryString());	
	}
	
	public Response actionVer_logs() throws IOException, IllegalArgumentException, IllegalAccessException{
		
		Session model = new Session();
		model.load();
		/*----#gen-example
		  EXAMPLES COPY/PASTE:
		  INFO: Core.query(null,... change 'null' to your db connection name added in application builder.
		 this.addQueryString("p_id","12"); //to send a query string in the URL
		 return this.forward("igrp","MapaProcesso","index", this.queryString()); //if submit, loads the values
		  ----#gen-example */
		/*----#start-code(ver_logs)----*/
		
		/*----#end-code----*/
		return this.redirect("igrp","MapaProcesso","index", this.queryString());	
	}
	
	/*----#start-code(custom_actions)----*/

	private Properties loadDbConfig(String filePath, String fileName) {
		
		String path = Config.BASE_PATH_CONFIGURATION + "/" + filePath;
		File file = new File(Objects.requireNonNull(getClass().getClassLoader().getResource(path + "/" + fileName)).getPath());
		
		Properties props = new Properties();
		try (FileInputStream fis = new FileInputStream(file)) {
			props.loadFromXML(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return props;
	}
	
	/*----#end-code----*/
	}
