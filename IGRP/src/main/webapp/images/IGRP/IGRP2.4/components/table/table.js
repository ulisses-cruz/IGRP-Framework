(function(){

    class Table {

        table = null;

        name = null;

        constructor(item) {

            this.name = $(item).attr('item-name');

        }

        filter(){
            const object = {};

            this.filterFields().serializeArray().forEach( (item,i)=>{
                object[item.name] = item.value;
            }); 

            var loadingb64 = $('<div>').addClass('xml-xsl-loader');
	 		loadingb64.css({
	 			'position':'absolute',
	 			'width':'100%',
	 			'height':'100%',
	 			'background-image':'url(data:image/gif;base64,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)',
	 			'background-color':'rgba(255,255,255,0.7)',
	 			'background-repeat':'no-repeat',
	 			'background-position':'center',
	 			'left':0,
	 			'top':0
	 		});

            this.html().addClass('xml-xsl-loading position-relative');

            this.html().append(loadingb64)
            
            $.get(this.filterUrl(),object).then( (d)=>{
             
                const { app, page } = $.IGRP.info;
                const clone = this.html().clone(true);
                $.IGRP.utils.xsl.transform({
                    xsl    : `${path}/app/${app}/${page}/${page}.xsl`,
                    xml    : d,
                    nodes  : ['table_1'],
                    customTransform : ({xml,xsl})=>{
                        
                        this.html().replaceWith(clone);

                        clone.XMLTransform({
                            xml     	 : xml,
                            xsl     	 : xsl,
                            loading      : true,
                            xslBasePath  : path+'/xsl/tmpl',
                            method 	     : 'replace',
                            complete     : (e,c)=>{
                                
                               /* const table = clone.find('.table-responsive');
                                
                                console.log( this.html().find('.table-responsive') )

                                this.html().find('.table-responsive').html( );

                                loadingb64.remove();*/
                                
                            }
                        });
                    }  
                    /*success: (d)=>{
                        console.log(d)
                    }*/
                });

               /* $.IGRP.utils.transformXMLNodes({
				
					nodes : ['table_1'],

					url   : $.IGRP.utils.getSubmitParams(actionURL,form,false),

					data  : form.serialize(),
					
					headers : {
						'X-IGRP-REMOTE' : 1
					},

					success:function(c){
						if ($.IGRP.components.tableCtrl.resetTableConfigurations)
							$.IGRP.components.tableCtrl.resetTableConfigurations(c.itemHTML);
					},

					error:function(){
						
						$.IGRP.utils.loading.hide();
					}

				});*/

            });

            return false;
        }

        
        html(){
            return $('.igrp-table[item-name="'+this.name+'"]')
        }

        filterFields(){
            return this.html().find('.fields :input[name]');
        }

        filterUrl(){
            return this.html().find('.card-filter').attr('remote-filter')
        }

  
    }

    const IGRPTableComponents = {

        items : {},

        get : (name) => IGRPTableComponents.items[name] || null,

        init:()=>{

            $('.igrp-table').each( function(i,item){
                const table = new Table( item );
                const name = table.name;
                IGRPTableComponents.items[name] = table;
            });

            $(document).on('click', '.igrp-table-filter-action', function(e){
                const actionBtn = this;
                const table = $(actionBtn).parents('.igrp-table');
                const tableID = table.attr('item-name');
                
                IGRPTableComponents.get(tableID)?.filter();
                
                return false;
            });

        }
    };


    $.IGRP.component('table_ext', IGRPTableComponents, true);

})();