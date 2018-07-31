
package nosi.webapps.igrp.pages.novodominio;
import nosi.core.webapp.Model;
import nosi.core.webapp.View;
import nosi.core.gui.components.*;
import nosi.core.gui.fields.*;
import static nosi.core.i18n.Translator.gt;

import nosi.core.config.Config;
import nosi.core.gui.components.IGRPLink;
import nosi.core.webapp.Report;



public class NovoDominioView extends View {

	public Field sectionheader_1_text;
	public Field dominio;
	public Field key;
	public Field description;
	public Field ordem;
	public Field estado;
	public Field id;
	public IGRPForm sectionheader_1;
	public IGRPForm form_1;
	public IGRPFormList formlist_1;

	public IGRPToolsBar toolsbar_1;
	public IGRPButton btn_gravar;

	public NovoDominioView(){

		this.setPageTitle("Registar Dominio");
			
		sectionheader_1 = new IGRPForm("sectionheader_1","");

		form_1 = new IGRPForm("form_1","");

		formlist_1 = new IGRPFormList("formlist_1","");

		sectionheader_1_text = new TextField(model,"sectionheader_1_text");
		sectionheader_1_text.setLabel(gt(""));
		sectionheader_1_text.setValue(gt("Gestão de Dominio - Novo"));
		sectionheader_1_text.propertie().add("type","text").add("name","p_sectionheader_1_text").add("maxlength","4000");
		
		dominio = new TextField(model,"dominio");
		dominio.setLabel(gt("Domínio"));
		dominio.propertie().add("name","p_dominio").add("type","text").add("maxlength","30").add("required","true");
		
		key = new TextField(model,"key");
		key.setLabel(gt("Chave"));
		key.propertie().add("name","p_key").add("type","text").add("maxlength","250").add("required","false").add("desc","true");
		
		description = new TextField(model,"description");
		description.setLabel(gt("Descrição"));
		description.propertie().add("name","p_description").add("type","text").add("maxlength","250").add("required","false").add("desc","true");
		
		ordem = new NumberField(model,"ordem");
		ordem.setLabel(gt("Ordem"));
		ordem.propertie().add("name","p_ordem").add("type","number").add("min","0").add("max","20").add("maxlength","250").add("required","false").add("java-type","").add("desc","true");
		
		estado = new ListField(model,"estado");
		estado.setLabel(gt("Estado"));
		estado.propertie().add("name","p_estado").add("type","select").add("multiple","false").add("tags","false").add("domain","SIN_NAO").add("maxlength","250").add("required","false").add("java-type","").add("delimiter",";").add("desc","true");
		
		id = new HiddenField(model,"id");
		id.setLabel(gt(""));
		id.propertie().add("name","p_id").add("type","hidden").add("maxlength","250").add("java-type","int").add("tag","id").add("desc","true");
		

		toolsbar_1 = new IGRPToolsBar("toolsbar_1");

		btn_gravar = new IGRPButton("Gravar","igrp","NovoDominio","gravar","submit","primary|fa-save","","");
		btn_gravar.propertie.add("type","specific").add("rel","gravar");

		
	}
		
	@Override
	public void render(){
		
		sectionheader_1.addField(sectionheader_1_text);


		form_1.addField(dominio);

		formlist_1.addField(key);
		formlist_1.addField(description);
		formlist_1.addField(ordem);
		formlist_1.addField(estado);
		formlist_1.addField(id);

		toolsbar_1.addButton(btn_gravar);
		this.addToPage(sectionheader_1);
		this.addToPage(form_1);
		this.addToPage(formlist_1);
		this.addToPage(toolsbar_1);
	}
		
	@Override
	public void setModel(Model model) {
		
		dominio.setValue(model);
		key.setValue(model);
		description.setValue(model);
		ordem.setValue(model);
		estado.setValue(model);
		id.setValue(model);	

		formlist_1.loadModel(((NovoDominio) model).getFormlist_1());
		
	}
}
