package nosi.webapps.igrp.pages.menuorganica;

import nosi.core.webapp.Controller;
import java.io.IOException;
import nosi.core.webapp.Core;
import nosi.core.webapp.Response;
/*----#start-code(packages_import)----*/
import nosi.core.webapp.Igrp;
import nosi.webapps.igrp.dao.Menu;
import nosi.webapps.igrp.dao.Organization;
import nosi.webapps.igrp.dao.Profile;
import nosi.webapps.igrp.dao.ProfileType;
import nosi.webapps.igrp.dao.User;
import java.util.ArrayList;
import java.util.List;
/*----#end-code----*/

public class MenuOrganicaController extends Controller {		

	public Response actionIndex() throws IOException, IllegalArgumentException, IllegalAccessException{
		
		MenuOrganica model = new MenuOrganica();
		model.load();
		MenuOrganicaView view = new MenuOrganicaView();
		/*----#gen-example
		  EXAMPLES COPY/PASTE:
		  INFO: Core.query(null,... change 'null' to your db connection name added in application builder.
		model.loadTable_1(Core.query(null,"SELECT 'menu' as menu,'descricao' as descricao "));
		  ----#gen-example */
		/*----#start-code(index)----*/
    
			ArrayList<MenuOrganica.Table_1> data = new ArrayList<>();
			List<Menu> menus = new ArrayList<>();
			int env_fk = Core.getParamInt("env_fk");
			User user = null;
			Profile profile = null;
			if (model.getType().equals("org")) {		
				menus = new Organization().getOrgMenu(env_fk,model.getId());
			} else if (model.getType().equals("perfil")) {
				ProfileType p = new ProfileType().findOne(model.getId());
				// ALL PROFILE org has org_fk = null so the org is 1
				if(p.getOrganization()!=null)
				  menus = new Organization().getPerfilMenu(p.getOrganization().getId(),p.getId()); 
				else
					  menus = new Organization().getPerfilMenu(1,p.getId()); 
				//new menu button invisible
                view.btn_novo.setVisible(false);              
			}else if(model.getType().equalsIgnoreCase("user")) {
				profile = new Profile().findOne(model.getId());
		      	user = new User().findIdentityByEmail(Core.getParam("userEmail"));
		      	if(user!=null && profile!=null)
		      		menus = new Organization().getOrgMenuByUser(profile.getOrganization().getId(),user.getId());
			}
			for (Menu m : menus) {
				if (m != null) {
					MenuOrganica.Table_1 table = new MenuOrganica.Table_1();
					table.setMenu(m.getId());
					if (m.isInserted()) {
						table.setMenu_check(m.getId());
					} else {
						table.setMenu_check(-1);
					}
					table.setDescricao(m.getDescr());
					data.add(table);
				}
			}

			view.table_1.addData(data);

		if (model.getType().equals("org")) {
			view.btn_novo.setLink("igrp","MenuOrganica","novo&env_fk=" + Core.getParam("env_fk"));		
			view.btn_novo.setVisible(true);
		}
		
		if(model.getType().equals("user") && user!=null && profile!=null) {
			view.btn_gravar.setLink("igrp","MenuOrganica","gravar&user_id=" + user.getId()+"&org_id="+profile.getOrganization().getId()+"&prof_id="+profile.getProfileType().getId());		
		}
		/*----#end-code----*/
		view.setModel(model);
		return this.renderView(view);	
	}
	
	public Response actionGravar() throws IOException, IllegalArgumentException, IllegalAccessException{
		
		MenuOrganica model = new MenuOrganica();
		model.load();
		/*----#gen-example
		  EXAMPLES COPY/PASTE:
		  INFO: Core.query(null,... change 'null' to your db connection name added in application builder.
		 this.addQueryString("p_id","12"); //to send a query string in the URL
		 return this.forward("igrp","MenuOrganica","index", this.queryString()); //if submit, loads the values
		  ----#gen-example */
		/*----#start-code(gravar)----*/

		Organization organization = new Organization();
		if (Igrp.getInstance().getRequest().getMethod().toUpperCase().equals("POST") ) {
			this.deleteOldMenu(model);
			if (model.getType().equals("org")) {
				organization = new Organization().findOne(model.getId());
			}
			this.assocNewsMenu(model);
		}
		if (model.getType().equals("org"))
			return this.forward("igrp", "MenuOrganica", "index&env_fk=" + organization.getApplication().getId());
		else if (model.getType().equals("perfil") || model.getType().equals("user"))          
			return this.forward("igrp", "MenuOrganica", "index");
		/*----#end-code----*/
		return this.redirect("igrp","MenuOrganica","index", this.queryString());	
	}
	


	

	public Response actionNovo() throws IOException, IllegalArgumentException, IllegalAccessException{
		
		MenuOrganica model = new MenuOrganica();
		model.load();
		/*----#gen-example
		  EXAMPLES COPY/PASTE:
		  INFO: Core.query(null,... change 'null' to your db connection name added in application builder.
		 this.addQueryString("p_id","12"); //to send a query string in the URL
		 return this.forward("igrp","NovoMenu","index", this.queryString()); //if submit, loads the values
		  ----#gen-example */
		/*----#start-code(novo)----*/
		int env_fk = Core.getParamInt("env_fk");
		this.addQueryString("app",env_fk); //to send a query string in the URL
		return this.forward("igrp","NovoMenu","index", this.queryString());		
		/*----#end-code----*/
			
	}
	
	/*----#start-code(custom_actions)----*/
	
	private User userAdmin = new User().getUserAdmin();
	private ProfileType profAdmin = new ProfileType().getProfileAdmin();
	
	private void deleteOldMenu(MenuOrganica model) {
		List<ProfileType> list = null;
		Organization organization1 = new Organization();
		Profile profD = new Profile();
		if (model.getType().equals("org")) {
			organization1 = new Organization().findOne(model.getId());
			profD.setOrganization(organization1);
			profD.setType("MEN");
			profD.setProfileType(profAdmin);
			profD.setUser(userAdmin);
			profD.deleteAllProfile();
		
			list = new ProfileType().find().andWhere("organization.id", "=", organization1.getId()).all();
			if (list != null && list.size() > 0) {
				list.sort((o1, o2) -> {
					if (o1.getId() > o2.getId())
						return 1;
					else if (o1.getId() < o2.getId())
						return -1;
					return 0;
				});
				ProfileType pAux = list.get(0);
				Profile pAux2 = new Profile();
				pAux2.setOrganization(organization1);
				pAux2.setType("MEN");
				pAux2.setUser(userAdmin);
				pAux2.setProfileType(pAux);
				pAux2.deleteAllProfile();
			}
			
		} else if (model.getType().equals("perfil")) {
			ProfileType pt = new ProfileType().findOne(model.getId());
			profD.setOrganization(pt.getOrganization());
			profD.setType("MEN");
			profD.setUser(userAdmin);
			profD.setProfileType(pt);
			profD.deleteAllProfile();
		} else if (model.getType().equals("user")) {
			profD.setOrganization(new Organization().findOne(Core.getParamInt("org_id")));
			profD.setType("MEN_USER");
			profD.setUser(new User().findOne(Core.getParamInt("user_id")));
			profD.setProfileType(new ProfileType().findOne(Core.getParamInt("prof_id")));
			profD.deleteAllProfile();
		}
	}
	
	private void assocNewsMenu(MenuOrganica model) {
		//TODO: mudar para Core.getParamArray()
		String[] mens = Igrp.getInstance().getRequest().getParameterValues("p_menu");
		
		if (mens != null && mens.length > 0) {
			List<ProfileType> list = null;
			for (String x : mens) {
				Profile prof = new Profile();
				prof.setUser(userAdmin);
				prof.setType("MEN");
				prof.setType_fk(Integer.parseInt(x.toString()));
				if (model.getType().equals("org")) {
					Organization aux = new Organization().findOne(model.getId());
					prof.setOrganization(aux);
					prof.setProfileType(profAdmin);

					/**  **/
					list = new ProfileType().find().andWhere("organization.id", "=", aux.getId()).all();
					if (list != null && list.size() > 0) {
						list.sort((o1, o2) -> {
							if (o1.getId() > o2.getId())
								return 1;
							else if (o1.getId() < o2.getId())
								return -1;
							return 0;
						});
						ProfileType pAux = list.get(0);
						Profile pAux2 = new Profile();
						pAux2.setUser(userAdmin);
						pAux2.setType("MEN");
						pAux2.setType_fk(Integer.parseInt(x.toString()));
						pAux2.setOrganization(aux);
						pAux2.setProfileType(pAux);
						pAux2.insert();
					}
					/**  **/

				} else if (model.getType().equals("perfil")) {
					ProfileType p = new ProfileType().findOne(model.getId());
					prof.setOrganization(p.getOrganization());
					prof.setProfileType(new ProfileType().findOne(model.getId()));
				}else if (model.getType().equals("user")) {
					prof.setType("MEN_USER");
					prof.setOrganization(new Organization().findOne(Core.getParamInt("org_id")));
					prof.setUser(new User().findOne(Core.getParamInt("user_id")));
					prof.setProfileType(new ProfileType().findOne(Core.getParamInt("prof_id")));
				}
				prof = prof.insert();
			}
		}
		Core.setMessageSuccess();
	}
	/*----#end-code----*/
	}
