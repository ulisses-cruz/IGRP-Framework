package nosi.webapps.igrp.pages.home;
/*---- Import your packages here... ----*/

import java.io.IOException;
import java.net.URLDecoder;
import nosi.core.webapp.Controller;
import nosi.core.webapp.Core;
import nosi.core.webapp.Response;
import nosi.core.webapp.helpers.ApplicationPermition;
import nosi.core.webapp.security.Permission;
import nosi.webapps.igrp.dao.Action;
import nosi.webapps.igrp.dao.Application;
import nosi.webapps.igrp.dao.Organization;
import nosi.webapps.igrp.dao.ProfileType;

/*---- End ----*/
public class HomeController extends Controller {		

	public Response actionIndex() throws IOException{ 
		String dad = Core.getParam("dad"); 
		if(dad != null) {
			dad = URLDecoder.decode(dad, "utf-8"); 
			String []aux = dad.split("\\|"); // Ex.: ENV|id|APP;ORG;PROF|p_id=1;p_type=3  
			if(aux != null) { 
				if(aux.length >= 2) { 
					String type = aux[0]; 
					String value = aux[1]; 
					String context = null; 
					String params = null; 
					if(aux.length == 4) {
						context = aux[2]; 
						params = aux[3]; 
					}
					String orgCode = null;
					String profCode = null; 
					String page = null; 
					
					if(context != null) { 
						String []allContext = context.split(";"); 
						if(allContext.length > 0) { 
							dad = allContext[0]; 
							if(allContext.length == 3) {
								orgCode = allContext[1]; 
								profCode = allContext[2]; 
								// inject session and cookie 
								injectOrgNProf(orgCode, profCode); 
							}
						}
					}
					
					switch (type) {
						case "ENV": 
							Application application = Core.findApplicationByDad(value); 
							if(application != null) {
								dad = application.getDad(); 
								nosi.webapps.igrp.dao.Action ac = application.getAction(); 
								page = "tutorial/DefaultPage/index&title=";
								if(ac != null && ac.getApplication()!=null) 
									page = ac.getApplication().getDad().toLowerCase() + "/" + ac.getPage() + "/index&title="+ac.getAction_descr();
							}
						break;
						case "PAGE":
							Action ac = new Action().findOne(Core.toInt(value)); 
							if(ac != null && ac.getApplication()!=null) 
								page = ac.getApplication().getDad().toLowerCase() + "/" + ac.getPage() + "/index&title="+ac.getAction_descr(); 
						break;
						case "ACTI":
							//Core.startTask(taskId)
						break;
						default:
							break;
					}
					
					if(params != null) 
						this.addQueryString("p_params", params); 
					
					this.addQueryString("app", dad); 
					this.addQueryString("page", page); // app + "/" + page + "/" + action; 
					
					return redirect("igrp_studio", "Env", "openApp", this.queryString()); 
				}
			}
		}
		
		if(Core.isNotNull(dad) && !dad.equals("igrp")) {
			nosi.webapps.igrp.dao.Action ac = Core.findApplicationByDad(dad).getAction();
			String page = "tutorial/DefaultPage/index&title=";
			if(ac!=null) { 
				page = ac.getPage();
				/**
				 * Go to home page of application or go to default page in case not exists home page associate to application
				 */
				page = (ac.getApplication()!=null)?(ac.getApplication().getDad().toLowerCase() + "/" + page):page+"/DefaultPage";
				page +="/index&title="+ac.getAction_descr();
			} 
			this.addQueryString("app", dad); 
			this.addQueryString("page", page); 
			
			return redirect("igrp_studio", "Env", "openApp", this.queryString()); 
		}else {
			try { // Eliminar 
				new Permission().changeOrgAndProfile("igrp"); 
			}catch(Exception e) { 
			}			
		}
		
		HomeView view = new HomeView(); 
		view.title = "Home"; 
		return this.renderView(view,true); 
	} 
	
	private void injectOrgNProf(String orgCode, String profCode) {
		Organization org = new Organization().find().where("code", "=", orgCode).orWhere("plsql_code", "=", orgCode).one();
		ProfileType prof = new ProfileType().find().where("code", "=", profCode).orWhere("plsql_code", "=", profCode).one();
		ApplicationPermition appP = new ApplicationPermition(prof.getOrganization().getApplication().getId(),
				prof.getOrganization().getApplication().getDad(), prof.getOrganization().getId(), prof.getId(),
				prof.getOrganization().getCode(),prof.getCode());
		Core.addToSession(appP.getDad(), appP); 
		new Permission().setCookie(appP); 
	}
}
