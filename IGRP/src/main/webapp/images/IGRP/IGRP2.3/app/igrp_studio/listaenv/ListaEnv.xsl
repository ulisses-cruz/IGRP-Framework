<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"><xsl:output method="html" omit-xml-declaration="yes" encoding="utf-8" indent="yes" doctype-system="about:legacy-compat"/><xsl:template match="/"><html><head><xsl:call-template name="IGRP-head"/><link rel="stylesheet" type="text/css" href="{$path}/core/igrp/toolsbar/toolsbar.css?v={$version}"/><link rel="stylesheet" type="text/css" href="{$path}/core/igrp/table/datatable/dataTables.bootstrap.css?v={$version}"/><link rel="stylesheet" type="text/css" href="{$path}/core/igrp/table/igrp.tables.css?v={$version}"/><style>th.estado input.IGRP_checkall{display:none;}</style></head><body class="{$bodyClass} sidebar-off"><xsl:call-template name="IGRP-topmenu"/><form method="POST" class="IGRP-form" name="formular_default" enctype="multipart/form-data"><div class="container-fluid"><div class="row"><xsl:call-template name="IGRP-sidebar"/><div class="col-sm-9 col-md-10 col-md-offset-2 col-sm-offset-3 main" id="igrp-contents"><div class="content"><div class="row row-msg"><div class="gen-column col-md-12"><div class="gen-inner"><xsl:apply-templates mode="igrp-messages" select="rows/content/messages"/></div></div></div><div class="row " id="row-7a6757f3"><div class="gen-column col-sm-6"><div class="gen-inner"><xsl:if test="rows/content/sectionheader_1"><section class="content-header gen-container-item " gen-class="" item-name="sectionheader_1"><h2><xsl:value-of disable-output-escaping="yes" select="rows/content/sectionheader_1/fields/sectionheader_1_text/value"/></h2></section></xsl:if></div></div><div class="gen-column col-sm-6"><div class="gen-inner"><xsl:if test="rows/content/toolsbar_1"><div class="toolsbar-holder default gen-container-item " gen-structure="toolsbar" gen-fields=".btns-holder&gt;a.btn" gen-class="" item-name="toolsbar_1"><div class="btns-holder  pull-right" role="group"><xsl:apply-templates select="rows/content/toolsbar_1" mode="gen-buttons"><xsl:with-param name="vertical" select="'true'"/><xsl:with-param name="outline" select="'false'"/></xsl:apply-templates></div></div></xsl:if></div></div></div><div class="row " id="row-aef5c1f1"><div class="gen-column col-md-12"><div class="gen-inner"><xsl:if test="rows/content/table_1"><div class="box box-table-contents gen-container-item stivij" gen-class="stivij" item-name="table_1"><div class="box-body "><div class="table-contents-head"><div class="table-contents-inner"></div></div><div class="table-box"><div class="table-box-inner"><table id="table_1" class="table table-striped  igrp-data-table IGRP_contextmenu " exports=""><thead><tr><xsl:if test="rows/content/table_1/fields/status"><th class="bs-checkbox gen-fields-holder estado" align="left"><label class="container-box checkbox-switch switch"><span><xsl:value-of select="rows/content/table_1/fields/status/label"/></span><input type="checkbox" class="IGRP_checkall" check-rel="status" data-title="Ativo?" data-toggle="tooltip"/><span class="checkmark"/></label></th></xsl:if><xsl:if test="rows/content/table_1/fields/name"><th align="left" class=" gen-fields-holder"><span><xsl:value-of select="rows/content/table_1/fields/name/label"/></span><i class="fa fa-link"/></th></xsl:if><xsl:if test="rows/content/table_1/fields/dad"><th align="left" class=" gen-fields-holder"><span><xsl:value-of select="rows/content/table_1/fields/dad/label"/></span></th></xsl:if><xsl:if test="rows/content/table_1/table/context-menu/item"><th class="igrp-table-ctx-th"/></xsl:if></tr></thead><tbody><xsl:for-each select="rows/content/table_1/table/value/row[not(@total='yes')]"><tr><xsl:apply-templates mode="context-param" select="context-menu"/><input type="hidden" name="p_id_fk" value="{id}"/><input type="hidden" name="p_id_fk_desc" value="{id_desc}"/><xsl:if test="status"><td align="left" data-row="{position()}" data-title="{../../label/status}" class="bs-checkbox estado" item-name="status" data-order="{status_check=status}"><xsl:if test="status != '-0'"><label class="container-box checkbox-switch switch"><input type="checkbox" name="p_status" value="{status}" check-rel="status" class="checkdcontrol"><xsl:if test="status_check=status"><xsl:attribute name="checked">checked
                              </xsl:attribute></xsl:if></input><span class="slider round"/><span class="checkmark"/><input type="hidden" name="p_status_check" class="status_check"><xsl:if test="status_check=status"><xsl:attribute name="value"><xsl:value-of select="status_check"/></xsl:attribute></xsl:if></input></label><xsl:if test="status_check!=status"><input type="hidden" name="p_status" value="{status}" class="status"/></xsl:if></xsl:if></td></xsl:if><xsl:if test="name"><td align="left" data-row="{position()}" data-title="{../../label/name}" class="link" item-name="name"><xsl:choose><xsl:when test="name != ''"><a href="{normalize-space(name)}" class="link bClick btn btn-link " target-fields="" target="_newtab" request-fields="" name="name"><span><xsl:value-of select="name_desc"/></span></a></xsl:when><xsl:otherwise><span><xsl:value-of select="name_desc"/></span></xsl:otherwise></xsl:choose></td></xsl:if><xsl:if test="dad"><td align="left" data-order="{dad}" data-row="{position()}" data-title="{../../../fields/dad/label}" class="text" item-name="dad"><span class=""><xsl:value-of select="dad"/></span></td></xsl:if><xsl:if test="//rows/content/table_1/table/context-menu/item"><td class="igrp-table-ctx-td"><xsl:apply-templates select="../../context-menu" mode="table-context-inline"><xsl:with-param name="row-params" select="context-menu"/></xsl:apply-templates></td></xsl:if></tr></xsl:for-each></tbody></table></div></div></div></div></xsl:if></div></div></div></div></div></div></div><xsl:call-template name="IGRP-bottom"/></form><script type="text/javascript" src="{$path}/core/igrp/form/igrp.forms.js?v={$version}"/><script type="text/javascript" src="{$path}/core/igrp/table/datatable/jquery.dataTables.min.js?v={$version}"/><script type="text/javascript" src="{$path}/core/igrp/table/datatable/dataTables.bootstrap.min.js?v={$version}"/><script type="text/javascript" src="{$path}/core/igrp/table/igrp.table.js?v={$version}"/><script type="text/javascript" src="{$path}/core/igrp/table/bootstrap-contextmenu.js?v={$version}"/><script type="text/javascript" src="{$path}/core/igrp/table/table.contextmenu.js?v={$version}"/><script>$('.stivij').on('change','td input[name="p_status"]',function(){        var isChecked = $(this).is(':checked'),        id = $(this).parents('tr').find('input[name="p_id_fk"]').val(),        val = isChecked ? 1 : 0;      console.log(val);        $.IGRP.request('webapps?r=igrp_studio/ListaEnv/changeStatus',{        params : {            p_id : id,p_status : val            },        success:function(r){            var type = r.status ? 'success' : 'danger',                msg = r.status ? 'Estado alterado!' : 'Estado não foi alterado!';                                if(r.status)                $.IGRP.notify({                    message : msg,                        type    : type                    });            },            fail:function(){                               $('input:checkbox', this).prop('checked', function (i, value) {        return !value;       });                        }        });    });   </script></body></html></xsl:template><xsl:include href="../../../xsl/tmpl/IGRP-functions.tmpl.xsl?v=25"/><xsl:include href="../../../xsl/tmpl/IGRP-variables.tmpl.xsl?v=25"/><xsl:include href="../../../xsl/tmpl/IGRP-home-include.tmpl.xsl?v=25"/><xsl:include href="../../../xsl/tmpl/IGRP-utils.tmpl.xsl?v=25"/><xsl:include href="../../../xsl/tmpl/IGRP-table-utils.tmpl.xsl?v=25"/></xsl:stylesheet>
