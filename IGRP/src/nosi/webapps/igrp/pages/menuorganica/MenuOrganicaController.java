package nosi.webapps.igrp.pages.menuorganica;

/*----#START-PRESERVED-AREA(PACKAGES_IMPORT)----*/
import nosi.core.webapp.Controller;
import nosi.core.webapp.Igrp;
import nosi.core.webapp.Response;
import nosi.webapps.igrp.dao.Menu;
import nosi.webapps.igrp.dao.Organization;
import nosi.webapps.igrp.dao.Profile;
import nosi.webapps.igrp.dao.ProfileType;
import nosi.webapps.igrp.dao.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static nosi.core.i18n.Translator.gt;
/*----#END-PRESERVED-AREA----*/

public class MenuOrganicaController extends Controller {		

	public Response actionIndex() throws IOException{
		/*----#START-PRESERVED-AREA(INDEX)----*/
		String type = Igrp.getInstance().getRequest().getParameter("type");
		String id = Igrp.getInstance().getRequest().getParameter("id");
		MenuOrganica model = new MenuOrganica();	
		MenuOrganicaView view = null;
		if(id!=null && type!=null){
			model.setId(id);
			model.setType(type);		
			ArrayList<MenuOrganica.Table_1> data = new ArrayList<>();
			List<Menu> menus = null;
			if(type.equals("org")){
				menus = new Organization().getOrgMenu();
			}else if(type.equals("perfil")){
				ProfileType p = new ProfileType().findOne(Integer.parseInt(id));
				menus = new Organization().getPerfilMenu(p.getOrganization()!=null?p.getOrganization().getId():1);
			}
			for(Menu m:menus){
				MenuOrganica.Table_1 table = new MenuOrganica().new Table_1();
				table.setMenu(m.getId());
				Profile prof = new Profile();
				prof.setOrganization(new Organization().findOne(Integer.parseInt(id)));
				prof.setProfileType(new ProfileType().findOne(0));
				prof.setUser(new User().findOne(0));
				prof.setType_fk(m.getId());
				if(type.equals("perfil")){
					ProfileType p = new ProfileType().findOne(Integer.parseInt(id));
					prof.setOrganization(p.getOrganization());
					prof.setProfileType(new ProfileType().findOne(p.getId()));
				}
				if((type.equals("org") && prof.isInsertedOrgMen()) || (type.equals("perfil") && prof.isInsertedPerfMen())){
					table.setMenu_check(m.getId());
				}else{
					table.setMenu_check(-1);
				}
				table.setDescricao(m.getDescr());
				data.add(table);
			}		
			view = new MenuOrganicaView(model);	
			view.table_1.addData(data);
		}
		return this.renderView(view);
		/*----#END-PRESERVED-AREA----*/
	}

	public Response actionGravar() throws IOException, IllegalArgumentException, IllegalAccessException, InterruptedException{
		/*----#START-PRESERVED-AREA(GRAVAR)----*/
		String id = Igrp.getInstance().getRequest().getParameter("id");
		String type = Igrp.getInstance().getRequest().getParameter("type");
		if(Igrp.getInstance().getRequest().getMethod().toUpperCase().equals("POST") && type!=null && id!=null){
			MenuOrganica model = new MenuOrganica();
			model.load();
			Profile profD = new Profile();
			if(type.equals("org")){
				profD.setOrganization(new Organization().findOne(Integer.parseInt(id)));
				profD.setType("MEN");
				profD.setProfileType(new ProfileType().findOne(0));
				profD.setUser(new User().findOne(0));
				profD.deleteAllProfile();
			}else if(type.equals("perfil")){
				ProfileType pt = new ProfileType().findOne(Integer.parseInt(id));
				profD.setOrganization(pt.getOrganization());
				profD.setType("MEN");
				profD.setUser(new User().findOne(0));
				profD.setProfileType(new ProfileType().findOne(Integer.parseInt(id)));
				profD.deleteAllProfile();
			}
			
			String[] mens = Igrp.getInstance().getRequest().getParameterValues("p_menu");
			if(mens!=null && mens.length>0){
				for(String x:mens){
					Profile prof = new Profile();
					prof.setUser(new User().findOne(0));
					prof.setType("MEN");
					prof.setType_fk(Integer.parseInt(x.toString()));
					if(type.equals("org")){
						prof.setOrganization(new Organization().findOne(Integer.parseInt(id)));
						prof.setProfileType(new ProfileType().findOne(0));
					}else if(type.equals("perfil")){
						ProfileType p = new ProfileType().findOne(Integer.parseInt(id));
						prof.setOrganization(p.getOrganization());
						prof.setProfileType(new ProfileType().findOne(Integer.parseInt(id)));
					}
					prof = prof.insert();
				}
			}
			Igrp.getInstance().getFlashMessage().addMessage("success", gt("Opera��o realizada com sucesso"));
		}
		return this.redirect("igrp", "MenuOrganica", "index","id="+id+"&type="+type);
		/*----#END-PRESERVED-AREA----*/
	}
	
	public Response actionVoltar() throws IOException{
		/*----#START-PRESERVED-AREA(VOLTAR)----*/
		return this.redirect("igrp","MenuOrganica","index");
		/*----#END-PRESERVED-AREA----*/
	}
	

	/*----#START-PRESERVED-AREA(CUSTOM_ACTIONS)----*/
	
	/*----#END-PRESERVED-AREA----*/
	
}