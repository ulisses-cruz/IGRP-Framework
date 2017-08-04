<xsl:stylesheet
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html" omit-xml-declaration="yes" encoding="ISO-8859-1" doctype-public="-//W3C//DTD XHTML 1.0 Transitional//EN" doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"/>
    <xsl:template match="/">
        <html>
            <head>
                <xsl:call-template name="IGRP-head"/>
                <!-- TOOLSBAR CSS INCLUDES -->
                <link rel="stylesheet" type="text/css" href="{$path}/core/igrp/toolsbar/toolsbar.css"/>
                <!-- FORM CSS INCLUDES -->
                <link rel="stylesheet" type="text/css" href="{$path}/core/igrp/form/igrp.forms.css"/>
                <!-- SELECT CSS INCLUDES -->
                <link rel="stylesheet" type="text/css" href="{$path}/plugins/select2/select2.min.css"/>
                <link rel="stylesheet" type="text/css" href="{$path}/plugins/select2/select2.style.css"/>
                <style/>
            </head>
            <body class="{$bodyClass} sidebar-off">
                <xsl:call-template name="IGRP-topmenu"/>
                <form method="POST" class="IGRP-form" name="formular_default" enctype="multipart/form-data">
                    <div class="container-fluid">
                        <div class="row">
                            <xsl:call-template name="IGRP-sidebar"/>
                            <div class="col-sm-9 col-md-10 col-md-offset-2 col-sm-offset-3 main" id="igrp-contents">
                                <div class="content">
                                    <div class="row">
                                        <div class="gen-column col-md-12">
                                            <div class="gen-inner">
                                                <xsl:apply-templates mode="igrp-messages" select="rows/content/messages"/>
                                                <xsl:if test="rows/content/toolsbar_1">
                                                    <div class="toolsbar-holder boxed gen-container-item " gen-structure="toolsbar" gen-fields=".btns-holder a.btn" gen-class="" item-name="toolsbar_1">
                                                        <div class="btns-holder  pull-right" role="group">
                                                            <xsl:apply-templates select="rows/content/toolsbar_1" mode="gen-buttons">
                                                                <xsl:with-param name="vertical" select="'true'"/>
                                                            </xsl:apply-templates>
                                                        </div>
                                                    </div>
                                                </xsl:if>
                                                <xsl:if test="rows/content/form_1">
                                                    <div class="box igrp-forms gen-container-item " gen-class="" item-name="form_1">
                                                        <div class="box-body">
                                                            <div role="form">
                                                                <xsl:apply-templates mode="form-hidden-fields" select="rows/content/form_1/fields"/>
                                                                <xsl:if test="rows/content/form_1/fields/tipo_base_dados">
                                                                    <div class="col-md-3 form-group  gen-fields-holder" item-name="tipo_base_dados" item-type="select" required="required">
                                                                        <label for="{rows/content/form_1/fields/tipo_base_dados/@name}">
                                                                            <xsl:value-of select="rows/content/form_1/fields/tipo_base_dados/label"/>
                                                                        </label>
                                                                        <select class="form-control select2 " id="form_1_tipo_base_dados" name="{rows/content/form_1/fields/tipo_base_dados/@name}" required="required">
                                                                            <xsl:for-each select="rows/content/form_1/fields/tipo_base_dados/list/option">
                                                                                <option value="{value}" label="{text}">
                                                                                    <xsl:if test="@selected='true'">
                                                                                        <xsl:attribute name="selected">selected</xsl:attribute>
                                                                                    </xsl:if>
                                                                                    <span>
                                                                                        <xsl:value-of select="text"/>
                                                                                    </span>
                                                                                </option>
                                                                            </xsl:for-each>
                                                                        </select>
                                                                    </div>
                                                                </xsl:if>
                                                                <xsl:if test="rows/content/form_1/fields/hostname">
                                                                    <div class="form-group col-md-3   gen-fields-holder" item-name="hostname" item-type="text" required="required">
                                                                        <label for="{rows/content/form_1/fields/hostname/@name}">
                                                                            <span>
                                                                                <xsl:value-of select="rows/content/form_1/fields/hostname/label"/>
                                                                            </span>
                                                                        </label>
                                                                        <input type="text" value="{rows/content/form_1/fields/hostname/value}" class="form-control " id="{rows/content/form_1/fields/hostname/@name}" name="{rows/content/form_1/fields/hostname/@name}" required="required" maxlength="100" placeholder=""></input>
                                                                    </div>
                                                                </xsl:if>
                                                                <xsl:if test="rows/content/form_1/fields/port">
                                                                    <div class="form-group col-md-3   gen-fields-holder" item-name="port" item-type="number" required="required">
                                                                        <label for="{rows/content/form_1/fields/port/@name}">
                                                                            <span>
                                                                                <xsl:value-of select="rows/content/form_1/fields/port/label"/>
                                                                            </span>
                                                                        </label>
                                                                        <input type="number" value="{rows/content/form_1/fields/port/value}" class="form-control " id="{rows/content/form_1/fields/port/@name}" name="{rows/content/form_1/fields/port/@name}" required="required" maxlength="30" placeholder=""></input>
                                                                    </div>
                                                                </xsl:if>
                                                                <xsl:if test="rows/content/form_1/fields/nome_de_bade_dados">
                                                                    <div class="form-group col-md-3   gen-fields-holder" item-name="nome_de_bade_dados" item-type="text" required="required">
                                                                        <label for="{rows/content/form_1/fields/nome_de_bade_dados/@name}">
                                                                            <span>
                                                                                <xsl:value-of select="rows/content/form_1/fields/nome_de_bade_dados/label"/>
                                                                            </span>
                                                                        </label>
                                                                        <input type="text" value="{rows/content/form_1/fields/nome_de_bade_dados/value}" class="form-control " id="{rows/content/form_1/fields/nome_de_bade_dados/@name}" name="{rows/content/form_1/fields/nome_de_bade_dados/@name}" required="required" maxlength="50" placeholder=""></input>
                                                                    </div>
                                                                </xsl:if>
                                                                <xsl:if test="rows/content/form_1/fields/username">
                                                                    <div class="form-group col-md-3   gen-fields-holder" item-name="username" item-type="text" required="required">
                                                                        <label for="{rows/content/form_1/fields/username/@name}">
                                                                            <span>
                                                                                <xsl:value-of select="rows/content/form_1/fields/username/label"/>
                                                                            </span>
                                                                        </label>
                                                                        <input type="text" value="{rows/content/form_1/fields/username/value}" class="form-control " id="{rows/content/form_1/fields/username/@name}" name="{rows/content/form_1/fields/username/@name}" required="required" maxlength="80" placeholder=""></input>
                                                                    </div>
                                                                </xsl:if>
                                                                <xsl:if test="rows/content/form_1/fields/password">
                                                                    <div class="form-group col-md-3   gen-fields-holder" item-name="password" item-type="password">
                                                                        <label for="{rows/content/form_1/fields/password/@name}">
                                                                            <span>
                                                                                <xsl:value-of select="rows/content/form_1/fields/password/label"/>
                                                                            </span>
                                                                        </label>
                                                                        <input type="password" value="{rows/content/form_1/fields/password/value}" class="form-control " id="{rows/content/form_1/fields/password/@name}" name="{rows/content/form_1/fields/password/@name}" maxlength="80" placeholder=""></input>
                                                                    </div>
                                                                </xsl:if>
                                                            </div>
                                                        </div>
                                                        <xsl:apply-templates select="rows/content/form_1/tools-bar" mode="form-buttons"/>
                                                    </div>
                                                </xsl:if>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <xsl:call-template name="IGRP-bottom"/>
                </form>
                <!-- FORM JS INCLUDES -->
                <script type="text/javascript" src="{$path}/core/igrp/form/igrp.forms.js"/>
                <!-- SELECT JS INCLUDES -->
                <script type="text/javascript" src="{$path}/plugins/select2/select2.full.min.js"/>
                <script type="text/javascript" src="{$path}/plugins/select2/select2.init.js"/>
            </body>
        </html>
    </xsl:template>
    <xsl:include href="../../../xsl/tmpl/IGRP-functions.tmpl.xsl?v=1501696793769"/>
    <xsl:include href="../../../xsl/tmpl/IGRP-variables.tmpl.xsl?v=1501696793769"/>
    <xsl:include href="../../../xsl/tmpl/IGRP-home-include.tmpl.xsl?v=1501696793769"/>
    <xsl:include href="../../../xsl/tmpl/IGRP-utils.tmpl.xsl?v=1501696793769"/>
    <xsl:include href="../../../xsl/tmpl/IGRP-form-utils.tmpl.xsl?v=1501696793769"/>
</xsl:stylesheet>
