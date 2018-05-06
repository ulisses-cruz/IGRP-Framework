<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
  <xsl:output method="html" omit-xml-declaration="yes" encoding="utf-8" indent="yes" doctype-system="about:legacy-compat"/>
  <xsl:template match="/"> 
    <html>
      <head>
        <xsl:call-template name="IGRP-head"/>
        <!-- TOOLSBAR CSS INCLUDES -->
        <link rel="stylesheet" type="text/css" href="{$path}/core/igrp/toolsbar/toolsbar.css?v={$version}"/>
        <!-- SELECT CSS INCLUDES -->
        <link rel="stylesheet" type="text/css" href="{$path}/plugins/select2/select2.min.css?v={$version}"/>
        <link rel="stylesheet" type="text/css" href="{$path}/plugins/select2/select2.style.css?v={$version}"/>
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
                  <div class="row row-msg">
                    <div class="gen-column col-md-12">
                      <div class="gen-inner">
                        <xsl:apply-templates mode="igrp-messages" select="rows/content/messages"/>
                      </div>
                    </div>
                  </div>
                  <div class="row " id="row-3f888b81">
                    <div class="gen-column col-md-12">
                      <div class="gen-inner">
                        <xsl:if test="rows/content/sectionheader_1">
                          <section class="content-header gen-container-item " gen-class="" item-name="sectionheader_1">
                            <h2>
                              <xsl:value-of select="rows/content/sectionheader_1/fields/sectionheader_1_text/value"/>
                            </h2>
                          </section>
                        </xsl:if>
                        <xsl:if test="rows/content/toolsbar_1">
                          <div class="toolsbar-holder boxed gen-container-item " gen-structure="toolsbar" gen-fields=".btns-holder&gt;a.btn" gen-class="" item-name="toolsbar_1">
                            <div class="btns-holder  pull-right" role="group">
                              <xsl:apply-templates select="rows/content/toolsbar_1" mode="gen-buttons">
                                <xsl:with-param name="vertical" select="'true'"/>
                                <xsl:with-param name="outline" select="'false'"/>
                              </xsl:apply-templates>
                            </div>
                          </div>
                        </xsl:if>
                        <xsl:if test="rows/content/form_1">
                          <div class="box igrp-forms gen-container-item " gen-class="" item-name="form_1">
                            <div class="box-body">
                              <div role="form">
                                <xsl:apply-templates mode="form-hidden-fields" select="rows/content/form_1/fields"/>
                                <xsl:if test="rows/content/form_1/fields/aplicacao">
                                  <div class="col-sm-3 form-group  gen-fields-holder" item-name="aplicacao" item-type="select" required="required">
                                    <label for="{rows/content/form_1/fields/aplicacao/@name}">
                                      <xsl:value-of select="rows/content/form_1/fields/aplicacao/label"/>
                                    </label>
                                    <select class="form-control select2 " id="form_1_aplicacao" name="{rows/content/form_1/fields/aplicacao/@name}" required="required">
                                      <xsl:call-template name="setAttributes">
                                        <xsl:with-param name="field" select="rows/content/form_1/fields/aplicacao"/>
                                      </xsl:call-template>
                                      <xsl:for-each select="rows/content/form_1/fields/aplicacao/list/option">
                                        <option value="{value}" label="{text}">
                                          <xsl:if test="@selected='true'">
                                            <xsl:attribute name="selected">selected
                                            </xsl:attribute>
                                          </xsl:if>
                                          <span>
                                            <xsl:value-of select="text"/>
                                          </span>
                                        </option>
                                      </xsl:for-each>
                                    </select>
                                  </div>
                                </xsl:if>
                                <xsl:if test="rows/content/form_1/fields/modulo">
                                  <div class="form-group col-sm-3   gen-fields-holder" item-name="modulo" item-type="text" required="required">
                                    <label for="{rows/content/form_1/fields/modulo/@name}">
                                      <span>
                                        <xsl:value-of select="rows/content/form_1/fields/modulo/label"/>
                                      </span>
                                    </label>
                                    <input type="text" value="{rows/content/form_1/fields/modulo/value}" class="form-control " id="{rows/content/form_1/fields/modulo/@name}" name="{rows/content/form_1/fields/modulo/@name}" required="required" maxlength="50" placeholder="">
                                      <xsl:call-template name="setAttributes">
                                        <xsl:with-param name="field" select="rows/content/form_1/fields/modulo"/>
                                      </xsl:call-template>
                                    </input>
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
        <script type="text/javascript" src="{$path}/core/igrp/form/igrp.forms.js?v={$version}"/>
        <!-- SELECT JS INCLUDES -->
        <script type="text/javascript" src="{$path}/plugins/select2/select2.full.min.js?v={$version}"/>
        <script type="text/javascript" src="{$path}/plugins/select2/select2.init.js?v={$version}"/>
      </body>
    </html>
  </xsl:template>
  <xsl:include href="../../../xsl/tmpl/IGRP-functions.tmpl.xsl?v=1525645049674"/>
  <xsl:include href="../../../xsl/tmpl/IGRP-variables.tmpl.xsl?v=1525645049674"/>
  <xsl:include href="../../../xsl/tmpl/IGRP-home-include.tmpl.xsl?v=1525645049674"/>
  <xsl:include href="../../../xsl/tmpl/IGRP-utils.tmpl.xsl?v=1525645049674"/>
  <xsl:include href="../../../xsl/tmpl/IGRP-form-utils.tmpl.xsl?v=1525645049674"/>
</xsl:stylesheet>
