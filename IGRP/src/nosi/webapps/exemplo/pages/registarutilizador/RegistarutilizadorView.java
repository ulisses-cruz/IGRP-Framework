
package nosi.webapps.exemplo.pages.registarutilizador;
import nosi.core.webapp.View;
import nosi.core.gui.components.*;
import nosi.core.gui.fields.*;
import static nosi.core.i18n.Translator.gt;

public class RegistarutilizadorView extends View {
	
	
	public Field sectionheader_1_text;
	public Field username;
	public Field senha;
	public Field email_1;
	public IGRPSectionHeader sectionheader_1;
	public IGRPForm form_1;

	public IGRPToolsBar toolsbar_1;
	public IGRPButton btn_gravar;
	public RegistarutilizadorView(Registarutilizador model){
		this.setPageTitle("RegistarUtilizador");
			
		sectionheader_1 = new IGRPSectionHeader("sectionheader_1","");
		form_1 = new IGRPForm("form_1","");
		sectionheader_1_text = new TextField(model,"sectionheader_1_text");
		sectionheader_1_text.setLabel(gt(""));
		
		sectionheader_1_text.setValue(gt("Registar Utilizador"));
		sectionheader_1_text.propertie().add("type","text").add("name","p_sectionheader_1_text").add("persist","true").add("maxlength","4000");
		username = new TextField(model,"username");
		username.setLabel(gt("Username"));
		
		username.propertie().add("name","p_username").add("type","text").add("maxlength","30").add("required","false").add("change","false").add("readonly","false").add("disabled","false").add("placeholder","").add("right","false");
		senha = new TextField(model,"senha");
		senha.setLabel(gt("Senha"));
		
		senha.propertie().add("name","p_senha").add("type","text").add("maxlength","30").add("required","false").add("change","false").add("readonly","false").add("disabled","false").add("placeholder","").add("right","false");
		email_1 = new EmailField(model,"email_1");
		email_1.setLabel(gt("Email"));
		
		email_1.propertie().add("name","p_email_1").add("type","email").add("maxlength","100").add("required","false").add("change","false").add("readonly","false").add("disabled","false").add("placeholder","").add("right","false");

		toolsbar_1 = new IGRPToolsBar("toolsbar_1");
		btn_gravar = new IGRPButton("Gravar","exemplo","Registarutilizador","gravar","submit_form","success|fa-save","","");
		btn_gravar.propertie.add("type","specific").add("code","").add("rel","gravar");
		
	}
		
	@Override
	public void render(){
		
		sectionheader_1.addField(sectionheader_1_text);


		form_1.addField(username);
		form_1.addField(senha);
		form_1.addField(email_1);

		toolsbar_1.addButton(btn_gravar);
		this.addToPage(sectionheader_1);
		this.addToPage(form_1);
		this.addToPage(toolsbar_1);
	}
}
