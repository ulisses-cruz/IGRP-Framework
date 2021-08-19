<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"><xsl:output method="html" omit-xml-declaration="yes" encoding="utf-8" indent="yes" doctype-system="about:legacy-compat"/><xsl:template match="/"><html><head><xsl:call-template name="IGRP-head"/><link rel="stylesheet" type="text/css" href="{$path}/core/igrp/toolsbar/toolsbar.css?v={$version}"/><link rel="stylesheet" type="text/css" href="{$path}/core/igrp/table/datatable/dataTables.bootstrap.css?v={$version}"/><link rel="stylesheet" type="text/css" href="{$path}/core/igrp/table/igrp.tables.css?v={$version}"/><link rel="stylesheet" type="text/css" href="{$path}/plugins/formlist/igrp.formlist.css?v={$version}"/><link rel="stylesheet" type="text/css" href="{$path}/core/igrp/table/dataTables.bootstrap.css?v={$version}"/><link rel="stylesheet" type="text/css" href="{$path}/plugins/select2/select2.min.css?v={$version}"/><link rel="stylesheet" type="text/css" href="{$path}/plugins/select2/select2.style.css?v={$version}"/><style>th span.checkmark{display:none;}th .container-box{padding-left:7px !important;}</style></head><body class="{$bodyClass} sidebar-off"><xsl:call-template name="IGRP-topmenu"/><form method="POST" class="IGRP-form" name="formular_default" enctype="multipart/form-data"><div class="container-fluid"><div class="row"><xsl:call-template name="IGRP-sidebar"/><div class="col-sm-9 col-md-10 col-md-offset-2 col-sm-offset-3 main" id="igrp-contents"><div class="content"><div class="row " id="row-31fa1feb"><div class="gen-column col-sm-12"><div class="gen-inner"><xsl:if test="rows/content/sectionheader_1"><section class="content-header gen-container-item " gen-class="" item-name="sectionheader_1"><h2 class="disable-output-escaping"><xsl:value-of disable-output-escaping="yes" select="rows/content/sectionheader_1/fields/sectionheader_1_text/value"/></h2></section></xsl:if><xsl:apply-templates mode="igrp-messages" select="rows/content/messages"/></div></div></div><div class="row " id="row-a26028d7"><div class="gen-column col-sm-4"><div class="gen-inner"><xsl:if test="rows/content/form_1"><div class="box igrp-forms gen-container-item " gen-class="" item-name="form_1"><div class="box-body"><div role="form"><xsl:apply-templates mode="form-hidden-fields" select="rows/content/form_1/fields"/><xsl:if test="rows/content/form_1/fields/aplicacao"><div class="col-sm-12 form-group  gen-fields-holder" item-name="aplicacao" item-type="select"><label for="{rows/content/form_1/fields/aplicacao/@name}"><xsl:value-of select="rows/content/form_1/fields/aplicacao/label"/></label><select class="form-control select2  " id="form_1_aplicacao" name="{rows/content/form_1/fields/aplicacao/@name}" placeholder=""><xsl:call-template name="setAttributes"><xsl:with-param name="field" select="rows/content/form_1/fields/aplicacao"/></xsl:call-template><xsl:for-each select="rows/content/form_1/fields/aplicacao/list/option"><option value="{value}" label="{text}"><xsl:if test="@selected='true'"><xsl:attribute name="selected">selected</xsl:attribute></xsl:if><span><xsl:value-of select="text"/></span></option></xsl:for-each></select></div></xsl:if></div></div><xsl:apply-templates select="rows/content/form_1/tools-bar" mode="form-buttons"/></div></xsl:if></div></div><div class="gen-column col-sm-3"><div class="gen-inner"/></div><div class="gen-column col-sm-3"><div class="gen-inner"/></div><div class="gen-column col-sm-2"><div class="gen-inner"><xsl:if test="rows/content/toolsbar_1"><div class="toolsbar-holder default gen-container-item " gen-structure="toolsbar" gen-fields=".btns-holder&gt;a.btn" gen-class="" item-name="toolsbar_1"><div class="btns-holder btn-group-lg btn-group-justified" role="group"><xsl:apply-templates select="rows/content/toolsbar_1" mode="gen-buttons"><xsl:with-param name="vertical" select="'true'"/><xsl:with-param name="outline" select="'false'"/></xsl:apply-templates></div></div></xsl:if></div></div></div><div class="row " id="row-820b2b41"><div class="gen-column col-sm-12"><div class="gen-inner"><xsl:if test="rows/content/table_1"><div class="box box-table-contents gen-container-item tabela" gen-class="tabela" item-name="table_1"><div class="box-body "><div class="table-contents-head"><div class="table-contents-inner"></div></div><div class="table-box"><div class="table-box-inner"><table id="table_1" class="table table-striped  igrp-data-table IGRP_contextmenu " exports=""><thead><tr><xsl:if test="rows/content/table_1/fields/t1_menu_principal"><th td-name="t1_menu_principal" align="left" show-label="true" class="plaintext  gen-fields-holder" group-in=""><span><xsl:value-of select="rows/content/table_1/fields/t1_menu_principal/label"/></span></th></xsl:if><xsl:if test="rows/content/table_1/fields/ativo"><th class="bs-checkbox gen-fields-holder ativo" align="center"><label class="container-box checkbox-switch switch"><span><xsl:value-of select="rows/content/table_1/fields/ativo/label"/></span><input type="checkbox" class="IGRP_checkall" check-rel="ativo" data-title="Ativo" data-toggle="tooltip"/><span class="checkmark"/></label></th></xsl:if><xsl:if test="rows/content/table_1/fields/ordem"><th td-name="ordem" align="left" show-label="true" class="number  gen-fields-holder" group-in=""><span><xsl:value-of select="rows/content/table_1/fields/ordem/label"/></span></th></xsl:if><xsl:if test="rows/content/table_1/fields/icon"><th td-name="icon" align="left" show-label="true" class="plaintext  gen-fields-holder" group-in=""><span><xsl:value-of select="rows/content/table_1/fields/icon/label"/></span></th></xsl:if><xsl:if test="rows/content/table_1/fields/table_titulo"><th td-name="table_titulo" align="left" show-label="true" class="plaintext  gen-fields-holder" group-in=""><span><xsl:value-of select="rows/content/table_1/fields/table_titulo/label"/></span></th></xsl:if><xsl:if test="rows/content/table_1/fields/pagina"><th td-name="pagina" align="left" show-label="true" class="plaintext  gen-fields-holder" group-in=""><span><xsl:value-of select="rows/content/table_1/fields/pagina/label"/></span></th></xsl:if><xsl:if test="rows/content/table_1/fields/checkbox"><th class="bs-checkbox gen-fields-holder pub" align="center"><label class="container-box "><span><xsl:value-of select="rows/content/table_1/fields/checkbox/label"/></span><input type="checkbox" class="IGRP_checkall" check-rel="checkbox" data-title="Público" data-toggle="tooltip"/><span class="checkmark"/></label></th></xsl:if><xsl:if test="rows/content/table_1/table/context-menu/item"><th class="igrp-table-ctx-th"/></xsl:if></tr></thead><tbody><xsl:for-each select="rows/content/table_1/table/value/row[not(@total='yes')]"><tr><xsl:apply-templates mode="context-param" select="context-menu"/><input type="hidden" name="p_id_fk" value="{id}"/><input type="hidden" name="p_id_fk_desc" value="{id_desc}"/><xsl:if test="t1_menu_principal"><td align="left" data-order="{t1_menu_principal}" data-row="{position()}" data-title="{../../../fields/t1_menu_principal/label}" class="plaintext" item-name="t1_menu_principal"><span class=" "><xsl:value-of select="t1_menu_principal"/></span></td></xsl:if><xsl:if test="ativo"><td align="center" data-row="{position()}" data-title="{../../label/ativo}" class="bs-checkbox ativo" item-name="ativo" data-order="{ativo_check=ativo}"><xsl:if test="ativo != '-0'"><label class="container-box checkbox-switch switch"><input type="checkbox" name="p_ativo_fk" value="{ativo}" check-rel="ativo" class="checkdcontrol"><xsl:if test="ativo_check=ativo"><xsl:attribute name="checked">checked</xsl:attribute></xsl:if></input><span class="slider round"/><span class="checkmark"/></label></xsl:if><input type="hidden" name="p_ativo_check_fk" class="ativo_check"><xsl:if test="ativo_check=ativo"><xsl:attribute name="value"><xsl:value-of select="ativo_check"/></xsl:attribute></xsl:if></input><xsl:if test="ativo_check!=ativo"><input type="hidden" name="p_ativo_fk" value="{ativo}" class="ativo"/></xsl:if></td></xsl:if><xsl:if test="ordem"><td align="left" data-order="{ordem}" data-row="{position()}" data-title="{../../../fields/ordem/label}" class="number" item-name="ordem"><span class=""><xsl:value-of select="ordem"/></span></td></xsl:if><xsl:if test="icon"><td align="left" data-order="{icon}" data-row="{position()}" data-title="{../../../fields/icon/label}" class="plaintext" item-name="icon"><span class=" "><xsl:value-of select="icon" disable-output-escaping="yes"/></span></td></xsl:if><xsl:if test="table_titulo"><td align="left" data-order="{table_titulo}" data-row="{position()}" data-title="{../../../fields/table_titulo/label}" class="plaintext" item-name="table_titulo"><span class=" "><xsl:value-of select="table_titulo"/></span></td></xsl:if><xsl:if test="pagina"><td align="left" data-order="{pagina}" data-row="{position()}" data-title="{../../../fields/pagina/label}" class="plaintext" item-name="pagina"><span class=" "><xsl:value-of select="pagina"/></span></td></xsl:if><xsl:if test="checkbox"><td align="center" data-row="{position()}" data-title="{../../label/checkbox}" class="bs-checkbox pub" item-name="checkbox" data-order="{checkbox_check=checkbox}"><xsl:if test="checkbox != '-0'"><label class="container-box "><input type="checkbox" name="p_checkbox_fk" value="{checkbox}" check-rel="checkbox" class="checkdcontrol"><xsl:if test="checkbox_check=checkbox"><xsl:attribute name="checked">checked</xsl:attribute></xsl:if></input><span class="slider round"/><span class="checkmark"/></label></xsl:if><input type="hidden" name="p_checkbox_check_fk" class="checkbox_check"><xsl:if test="checkbox_check=checkbox"><xsl:attribute name="value"><xsl:value-of select="checkbox_check"/></xsl:attribute></xsl:if></input><xsl:if test="checkbox_check!=checkbox"><input type="hidden" name="p_checkbox_fk" value="{checkbox}" class="checkbox"/></xsl:if></td></xsl:if><xsl:if test="//rows/content/table_1/table/context-menu/item"><td class="igrp-table-ctx-td"><xsl:apply-templates select="../../context-menu" mode="table-context-inline"><xsl:with-param name="row-params" select="context-menu"/><xsl:with-param name="type" select="'inl'"/></xsl:apply-templates></td></xsl:if></tr></xsl:for-each></tbody></table></div></div></div></div></xsl:if><xsl:if test="rows/content/box_1"><div class="box igrp-box-holder gen-container-item " gen-class="" item-name="box_1"><xsl:call-template name="box-header"><xsl:with-param name="title" select="rows/content/box_1/@title"/><xsl:with-param name="collapsible" select="'true'"/><xsl:with-param name="collapsed" select="'true'"/></xsl:call-template><div class="box-body" gen-preserve-content="true"><xsl:apply-templates mode="form-hidden-fields" select="rows/content/box_1/fields"/><div><div class="row " id="row-1f2597ed"><div class="gen-column col-sm-12"><div class="gen-inner"><xsl:if test="rows/content/formlist_1"><div class="box box-table-contents gen-container-item " gen-class="" item-name="formlist_1"><div class="box-body table-box"><xsl:apply-templates mode="form-hidden-fields" select="rows/content/formlist_1/fields"/><table id="formlist_1" class="table table-striped gen-data-table ordertable IGRP_formlist noupdate nodelete  " rel="T_formlist_1" data-control="data-formlist_1"><thead><tr><xsl:if test="rows/content/formlist_1/fields/pagina_order"><th td-name="pagina_order" align="" show-label="" class="text  gen-fields-holder" group-in=""><span><xsl:value-of select="rows/content/formlist_1/fields/pagina_order/label"/></span></th></xsl:if><xsl:if test="not(rows/content/formlist_1/table/value/row[position() = 1]/@noupdate) or not(rows/content/formlist_1/table/value/row[position() = 1]/@nodelete)"><th class="table-btn add"><xsl:if test="not(rows/content/formlist_1/table/value/row[position() = 1]/@noupdate)"><a class="formlist-row-add btn btn-primary" rel="formlist_1" title="Add" data-toggle="tooltip" data-placement="left"><i class="fa fa-plus"/></a></xsl:if></th></xsl:if></tr></thead><tbody><xsl:for-each select="rows/content/formlist_1/table/value/row[not(@total='yes')]"><tr row="{position()}"><input type="hidden" name="p_formlist_1_id" value="{formlist_1_id}"/><input type="hidden" name="p_id_page_ord_fk" value="{id_page_ord}"/><input type="hidden" name="p_id_page_ord_fk_desc" value="{id_page_ord_desc}"/><input type="hidden" name="p_id_pai_fk" value="{id_pai}"/><input type="hidden" name="p_id_pai_fk_desc" value="{id_pai_desc}"/><input type="hidden" name="p_id_do_pai_fk" value="{id_do_pai}"/><input type="hidden" name="p_id_do_pai_fk_desc" value="{id_do_pai_desc}"/><xsl:apply-templates mode="form-hidden-fields" select="."/><xsl:if test="pagina_order"><xsl:if test="not(pagina_order/@visible)"><td align="" data-row="{position()}" data-title="{../../../fields/pagina_order/label}" class="text" item-name="pagina_order"><input type="hidden" name="{../../../fields/pagina_order/@name}_fk_desc" value="{pagina_order_desc}"/><div class="form-group" item-name="pagina_order" item-type="text"><input type="text" name="{../../../fields/pagina_order/@name}_fk" value="{pagina_order}" class="text form-control" rel="F_formlist_1" placeholder=""><xsl:call-template name="setAttributes"><xsl:with-param name="field" select="rows/content/formlist_1/fields/pagina_order"/></xsl:call-template></input></div></td></xsl:if></xsl:if><xsl:if test="not(@nodelete) or not(@noupdate)"><td class="table-btn delete" data-row="{position()}"><xsl:if test="not(@nodelete)"><span class="formlist-row-remove btn btn-danger" rel="formlist_1" title="Remove" data-toggle="tooltip" data-placement="bottom"><i class="fa fa-times"/></span></xsl:if></td></xsl:if></tr></xsl:for-each></tbody></table></div></div></xsl:if><xsl:if test="rows/content/toolsbar_2"><div class="toolsbar-holder default gen-container-item " gen-structure="toolsbar" gen-fields=".btns-holder&gt;a.btn" gen-class="" item-name="toolsbar_2"><div class="btns-holder   pull-right" role="group"><xsl:apply-templates select="rows/content/toolsbar_2" mode="gen-buttons"><xsl:with-param name="vertical" select="'true'"/><xsl:with-param name="outline" select="'false'"/></xsl:apply-templates></div></div></xsl:if></div></div></div></div></div></div></xsl:if></div></div></div></div></div></div></div><xsl:call-template name="IGRP-bottom"/></form><script type="text/javascript" src="{$path}/core/igrp/form/igrp.forms.js?v={$version}"/><script type="text/javascript" src="{$path}/core/igrp/table/datatable/jquery.dataTables.min.js?v={$version}"/><script type="text/javascript" src="{$path}/core/igrp/table/datatable/dataTables.bootstrap.min.js?v={$version}"/><script type="text/javascript" src="{$path}/core/igrp/table/igrp.table.js?v={$version}"/><script type="text/javascript" src="{$path}/core/igrp/table/bootstrap-contextmenu.js?v={$version}"/><script type="text/javascript" src="{$path}/core/igrp/table/table.contextmenu.js?v={$version}"/><script type="text/javascript" src="{$path}/core/formgen/js/jquery-ui.min.js?v={$version}"/><script type="text/javascript" src="{$path}/plugins/formlist/igrp.formlist.js?v={$version}"/><script type="text/javascript" src="{$path}/plugins/select2/select2.full.min.js?v={$version}"/><script type="text/javascript" src="{$path}/plugins/select2/select2.init.js?v={$version}"/><script src="{$path}/core/igrp/IGRP.rules.class.js"/><script>
$.IGRP.rules.set({"p_aplicacao":[{"name":"Show org e menu pai","events":"load,change","isTable":false,"conditions":{"rules":[{"condition":"null","value":"1","value2":"","patern":"","patern_custom":"","opposite":"1"}],"actions":[{"action":"hide","targets":"table_1,box_1","procedure":"","request_fields":"","msg_type":"info","msg":""}]}},{"name":"remote list","events":"change","isTable":false,"conditions":{"rules":[{"condition":"notnull","value":"","value2":"","patern":"","patern_custom":"","opposite":""}],"actions":[{"action":"remote_list","targets":"toolsbar_1,table_1,box_1","procedure":"webapps?r=igrp/PesquisarMenu/index","request_fields":"aplicacao,id_app","msg_type":"","msg":""}]}},{"name":"remote id app","events":"change","isTable":false,"conditions":{"rules":[{"condition":"notnull","value":"","value2":"","patern":"","patern_custom":"","opposite":""}],"actions":[{"action":"remote_list","targets":"id_app","procedure":"webapps?r=igrp/PesquisarMenu/index","request_fields":"aplicacao","msg_type":"","msg":""}]}}],"p_ativo":[{"name":"Switch state","events":"change","isTable":true,"conditions":{"rules":[{"condition":"","value":"","value2":"","patern":"","patern_custom":"","opposite":""}],"actions":[{"action":"remote","targets":"ativo","procedure":"webapps?r=igrp/PesquisarMenu/changeStatus","request_fields":"ativo,id","msg_type":"info","msg":""},{"action":"message","targets":"","procedure":"","request_fields":"","msg_type":"info","msg":"Changed"}]}}]},'actionsList');</script><script>$('.tabela').on('change','td input[name="p_ativo"]',function(){            var isChecked = $(this).is(':checked'),        id = $(this).parents('tr').find('input[name="p_id_fk"]').val(),        val = isChecked ? 1 : 0;        $.IGRP.request('webapps?r=igrp/PesquisarMenu/changeStatus',{        params : {            p_id : id,p_status : val            },        success:function(r){            var type = r.status ? 'success' : 'danger',                msg = r.status ? 'Estado alterado!' : 'Estado não foi alterado!';                                if(r.status)                $.IGRP.notify({                    message : msg,                        type    : type                    });            }, fail:function(){                               $('input:checkbox', this).prop('checked', function (i, value) {        return !value;       });                        }        });            });    </script></body></html></xsl:template><xsl:include href="../../../xsl/tmpl/IGRP-functions.tmpl.xsl?v=18"/><xsl:include href="../../../xsl/tmpl/IGRP-variables.tmpl.xsl?v=18"/><xsl:include href="../../../xsl/tmpl/IGRP-home-include.tmpl.xsl?v=18"/><xsl:include href="../../../xsl/tmpl/IGRP-utils.tmpl.xsl?v=18"/><xsl:include href="../../../xsl/tmpl/IGRP-form-utils.tmpl.xsl?v=18"/><xsl:include href="../../../xsl/tmpl/IGRP-table-utils.tmpl.xsl?v=18"/></xsl:stylesheet>
