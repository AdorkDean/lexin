(function($){$.extend($.fn,{getCss:function(key){var v=parseInt(this.css(key));if(isNaN(v))return false;return v;}});$.fn.Drags=function(opts){var ps=$.extend({zIndex:20,opacity:.7,handler:null,onMove:function(){},onDrop:function(){},range:''},opts);var dragndrop={drag:function(e){var dragData=e.data.dragData;var r=dragData.newRange;var dx=dragData.left+e.pageX-dragData.offLeft;var dy=dragData.top+e.pageY-dragData.offTop;if(typeof r=='object')dragData.target.css({left:dx<r[3]?r[3]:(dx>r[1]?r[1]:dx),top:dy<r[0]?r[0]:(dy>r[2]?r[2]:dy)});else if(r=='')dragData.target.css({left:dx,top:dy});dragData.target.css({'opacity':ps.opacity,'cursor':'move'});dragData.onMove(e);},drop:function(e){var dragData=e.data.dragData;dragData.target.css(dragData.oldCss);dragData.handler.css('cursor',dragData.oldCss.cursor);dragData.onDrop(e);$(document).unbind('mousemove',dragndrop.drag).unbind('mouseup',dragndrop.drop);}};return this.each(function(){var me=this;var handler=null;if(typeof ps.handler=='undefined'||ps.handler==null)handler=$(me);else handler=(typeof ps.handler=='string'?$(ps.handler,this):ps.handler);handler.bind('mousedown',{e:me},function(s){var target=$(s.data.e);var oldCss={};if(target.css('position')!='absolute'&&target.css('position')!='fixed'){try{target.position(oldCss);}catch(ex){}target.css('position','absolute');}oldCss.cursor=target.css('cursor')||'default';oldCss.opacity=target.getCss('opacity')||1;var newRange=[];if(ps.range=='window'){newRange=[target.css('position')=='fixed'?0:$(window).scrollTop(),target.css('position')=='fixed'?$(window).width()-target.outerWidth():$(window).scrollLeft()+$(window).width()-target.outerWidth(),target.css('position')=='fixed'?$(window).height()-target.outerHeight():$(window).scrollTop()+$(window).height()-target.outerHeight(),target.css('position')=='fixed'?0:$(window).scrollLeft()];}else{newRange=[ps.range[0],ps.range[1]-target.outerWidth(),ps.range[2]-target.outerWidth(),ps.range[3]];}var dragData={left:oldCss.left||target.getCss('left')||0,top:oldCss.top||target.getCss('top')||0,offLeft:target.css('position')=='fixed'?s.pageX-$(window).scrollLeft():s.pageX,offTop:target.css('position')=='fixed'?s.pageY-$(window).scrollLeft():s.pageY,oldCss:oldCss,onMove:ps.onMove,onDrop:ps.onDrop,handler:handler,target:target,newRange:newRange};target.css({'cursor':'move'});$(document).bind('mousemove',{dragData:dragData},dragndrop.drag).bind('mouseup',{dragData:dragData},dragndrop.drop);});});}})(jQuery);
(function($){ 
	$.bcbox=function(opts){
		if(typeof(opts)=='string'){
			var content = options;
			opts = {content:content};
		}
		// extend opts, set default options
		opts = opts || {};
		opts.width = (opts.width || 430) > $('body').width() ? $('body').width() : (opts.width || 430);
		opts.height = (opts.height || 210) > $('body').height() ? $('body').height() : (opts.height || 210);
		opts.title = opts.title || '标题';
		opts.content = opts.content || '欢迎使用juqery.bcbox';
		opts.bgOpacity = opts.bgOpacity || 0.6;
		opts.cache = typeof opts.cache == 'undefined' ? false : opts.cache;
		this.opts = opts;
		this._hook_options();
		this.csslink=$("<link/>").attr({rel:"stylesheet",
	        type: "text/css",
	        href: "/js/bcbox/jquery.bcbox.css"
	    }).appendTo("body");
		this.background = $('<div/>').css({
			//'position':'fixed',
			//'width': $(window).width() + 'px',
			//'height': $(window).height() + 'px',
			'z-index': 900,
			//'opacity': this.opts.bgOpacity,
			//'top': 0,
			//'left': 0,
			//'display':'none'
		}).appendTo('body').addClass('bcbox-background');
		
		this.main = $('<div/>').css({
			'bottom':'auto',
			'width': this.opts.width + 'px',
			'position': 'fixed',
			'z-index': 999,
			'display': 'none',
			'left': ($(window).width() - this.opts.width) / 2 + 'px',
			'top' : ($(window).height() - this.opts.height) / 2 + 'px'
		}).appendTo('body').addClass('bcbox-main');
//		bottom: auto;
//		left: 480px;
//		margin-left: auto;
//		margin-top: auto;
//		right: auto;
//		top: 79px;
//		visibility: visible;
//		width: 500px;
//		z-index: 10009;
		this.head = $('<div/>').appendTo(this.main).addClass('bcbox-head');
		
		this.title = $('<div/>').css({
			'float': 'left',
			'width': (this.opts.width - 50) + 'px'
		}).html(this.opts.title).appendTo(this.head).addClass('bcbox-title');
		
		var _this = this;
		this.closediv = $('<div/>').css({
			'text-align': 'right'
		}).append('<a href="javascript:;">×</a>').appendTo(this.head).find('a').click(function(){
			_this.close();
		}).addClass('bcbox-close');
		
		this.contentDiv = $('<div/>').css({ 
			'display': 'block',
			'width': this.opts.width + 'px',
			'height': (this.opts.height - 24) + 'px',
			'overflow': 'auto' 
		}).appendTo(this.main);
		
		this.content = $('<div/>').appendTo(this.contentDiv).html(this.opts.content).addClass('bcbox-content');
		this._hook_domready();
		return this;
	};
	$.bcbox.prototype = {
		show: function(){
			this.background.show();
			this.main.show();
			return this;
		},
		close:function(){
			if( this.opts.cache ){
				this.background.hide();
				this.main.hide();
			}else{
				this.remove();
			}
			return this;
		},
		remove:function(){
			this.background.remove();
			this.main.remove();
			return this;
		},
		_hook_options: function() {
			for (var pluginName in $.bcbox)
				if (this.is_active(pluginName) && $.bcbox[pluginName].options)
					$.bcbox[pluginName].options.apply(this, arguments);
		},

		// after dom setup
		_hook_domready: function() {
			for (var pluginName in $.bcbox)
				if (this.is_active(pluginName) && $.bcbox[pluginName].domready)
					$.bcbox[pluginName].domready.apply(this, arguments);
		},
		
		/*****************************************
		 *  helpers for plugins
		 *****************************************/
		
		// helper for rewrite the existed member functions, or add opts
		extend: function(name, v){
			if( $.isFunction(this[name]) && !/^_|^extend$|^is_active$/.test(name) ){
				// inline functions and '_' prefixed functions  are not allowed to be extended
				var self = this[name];
				this[name] = function(){
					v.call(this, self);
					return this;
				}
			} else {
				if( typeof this.opts[name] == 'undefined' )
					this.opts[name] = v;
			}
			return this;
		},
		
		// if a plugin is activated, for plugin development
		is_active: function(pluginName){
			return !$.bcbox[pluginName] || $.inArray(pluginName, this.opts.pluginsOn) > -1 ||
				( $.inArray(pluginName, this.opts.pluginsOff) < 0 &&
				  ( typeof $.bcbox[pluginName].active == 'undefined' || $.bcbox[pluginName].active )
				);
		}
	};
	$.bcbox.dragndrop = {//自由拖动
		options: function() {
			this.extend('allowDrag', true);
		},
		domready: function() {
			var _this = this;
			if (this.opts.allowDrag) {
				this.main.Drags({
					handler: this.title,
					range: 'window',
					onDrop: function() {
						_this.main.css('position', 'fixed');
						_this.relTop = _this.main.offset().top - $(window).scrollTop();
						_this.relLeft = _this.main.offset().left - $(window).scrollLeft();
					}
				});
			}
		}
	};
	$.bcbox.onclose = {//触发关闭后发生事件
		options: function(){
			this.extend('onClose', function(){});
		},
		domready: function(){
			this.extend('close', function(self){
				self.call(this);
				this.opts.onClose.apply(this, arguments);
			});
		}
	};
	$.bcbox.autoclose = {//自动关闭时间
		options: function() {
			this.extend('autoClose', 0);
		},
		domready: function(){
			var _this = this;
			this.closeMsg = '&nbsp; &nbsp; <strong>{seconds}</strong>秒之后关闭';
			var interval = null;
			var autoclose = function(sec){
				_this.title.append(_this.closeMsg.replace('{seconds}', sec));
				interval = setInterval(function(){
					if(sec==1){
						_this.close();
					}else{
						sec--;
						$('strong', _this.title).text(sec);
					}
				}, 1000);
			};
			this.extend('close', function(self){
				self.call(this);
				clearInterval(interval);
			});
			this.startAutoClose = function(sec){
				if( sec > 0 )
					autoclose.call(_this, sec);
				return _this;
			};
			this.endAutoClose = function(){
				_this.titleWrap.html( this.opts.title );
				clearInterval(interval);
				return _this;
			};
			this.startAutoClose(this.opts.autoClose);
		}
	}
	$.bcbox.contenttype = {
		options : function(){
			this.extend('type', 'text');
			this.extend('onAjaxed', function(){});
		},
		domready: function(){
			var _this = this;
			switch(this.opts.type){
				case 'alert':
					var closebutton = $('<input type="button" value="确定"/>').click(function(){
						_this.close();	
					});
					var center = $('<center/>').css('padding','8px').append(closebutton);
					this.main.append(center);
					// fix contentWrapWrap height
					_this.extend('show', function(self){
						self.apply(this, arguments);
						_this.contentDiv.height(this.opts.height - this.head.outerHeight(true) - center.outerHeight(true));
					});
					break;
				case 'confirm':
					_this.contentDiv.height(parseInt(_this.contentDiv.css('height'))-40);
					this.setValue = function(v){
						_this.value = v;
						return _this;
					};
					this.getValue = function(){
						return _this.value;
					}
					var yesbutton = $('<input type="button" value="确定"/>').click(function(){
						_this.setValue( true );
						_this.close();	
					});
					var nobutton = $('<input type="button" value="取消"/>').click(function(){
					_this.setValue( false );
						_this.close();	
					});
					var center = $('<center/>').css('padding','8px').append(yesbutton).append('&nbsp;').append(nobutton);
					this.main.append(center);
					// fix contentDiv height
					_this.extend('show', function(self){
						self.apply(this, arguments);
						_this.contentDiv.height(this.opts.height - this.head.outerHeight(true) - center.outerHeight(true));
					});
					break;
				case 'input':
					_this.contentDiv.height(parseInt(_this.contentDiv.css('height'))-40);
					this.setValue = function(v){
						_this.value = v;
						return _this;
					};
					this.getValue = function(){
						return _this.value;
					}
					var inputbox = $('<input type="text"/>');
					var okbutton = $('<input type="button" value="OK"/>').click(function(){
						_this.setValue( inputbox.val() );
						_this.close();
					});
					var div = $('<div/>').css('padding','8px').append(inputbox).append('&nbsp;').append(okbutton);
					this.main.append(div);
					// fix contentDiv height
					_this.extend('show', function(self){
						self.apply(this, arguments);
						_this.contentDiv.height(this.opts.height - this.head.outerHeight(true) - div.outerHeight(true));
					});
					break;
				case 'ajax':
				case 'url':
					_this.content.text('Loading ...');
					if( !_this.is_active('animate') )
						_this.startLoading = true;
					var interval = setInterval(function(){ // avoid choking while animating
						if( _this.startLoading ){
							clearInterval(interval);
							_this.content.load(_this.opts.content, function(){
								_this.opts.onAjaxed.apply(_this, arguments);
							});
						}
					}, 200);
					break;
				case 'iframe':
					this.content.css('padding',0).empty();
					if( !_this.is_active('animate') )
						_this.startLoading = true;
					var interval = setInterval(function(){
						if( _this.startLoading ){ 
							clearInterval(interval);
							$('<iframe border="0" frameborder="0" marginheight="0" marginwidth="0" scrolling="auto"></iframe>')
							.appendTo(_this.content)
							.css({
								'height': '100%',
								'width': '100%'
							})
							.attr({
								'width' : '100%',
								'height': '100%',
								'src'   : _this.opts.content
							});
						}
					}, 200);
					break;
				default:
					break;
			}
		}
	}
	$.bcbox.bgdblclick2close = {//允许双击背景关闭弹窗
		domready: function(){
			var _this = this;
			this.background.dblclick(function(){
				_this.close();	
			})
		}
	}
//	$.bcbox.resetposition = {//重置窗口位置
//		domready: function(){
//			var _this = this;
//			var resetPos = function(){
//				_this.background.css({
//					width: $(window).width() + 'px',
//					height: $(window).height() + 'px',
//					top: 0,
//					left: 0
//				});
//				if( _this.relTop ){  // have been dragged
//					_this.main.css({
//						top: _this.relTop + 'px',
//						left: _this.relLeft + 'px' 
//					});
//				} 
//			}
//			resetPos();
//			$(window)
//			.load(resetPos)    // just in case user is changing size of page while loading
//			.resize(resetPos)
//			.scroll(resetPos);
//		}
//	}
//	$.msgbox.closeicon = {//关闭图标设置
//			options: function(){
//				this.extend('closeIcon', '×');
//				if( !/^text:|^image:/.test(this.opts.closeIcon) )
//					this.opts.closeIcon = 'text:' + this.opts.closeIcon;
//			},
//			domready: function(){
//				if( /^text:/.test(this.opts.closeIcon) ){
//					this.closeWrap.html(this.opts.closeIcon.substr(5));
//				} else {
//					this.closeWrap.html('<img src="'+ this.opts.closeIcon.substr(6) +'" />');
//				}
//			}
//		}
//	$.msgbox.animate = {//动画
//			options: function(){
//				this.extend('anim', 0);
//			},
//			domready: function(){
//				var _this = this;
//				this.extend('show', function(self){
//					this.background.show();
//					switch(this.opts.anim){
//						case 0:
//							_this.mainWrap.show().css({
//								top: (_this.mainWrap.offset().top - 40) + 'px',
//								opacity: .3
//							}).animate({
//								top: (_this.mainWrap.offset().top + 40) + 'px',
//								opacity: 1
//							}, function(){
//								// loading start, avoid choking when loading content and animation happen at the same time
//								_this.startLoading = true; 
//								self.call(_this);
//							});
//							break;
//						case 1:
//							_this.mainWrap.show().css({
//								left: (_this.mainWrap.offset().left - 80) + 'px',
//								opacity: .3
//							}).animate({
//								left: (_this.mainWrap.offset().left + 80) + 'px',
//								opacity: 1
//							}, function(){
//								// loading start, avoid choking when loading content and animation happen at the same time
//								_this.startLoading = true; 
//								self.call(_this);
//							});
//							break;
//						case 2:
//							_this.mainWrap.show().css({
//								left: (_this.mainWrap.offset().left + 80) + 'px',
//								opacity: .3
//							}).animate({
//								left: (_this.mainWrap.offset().left - 80) + 'px',
//								opacity: 1
//							}, function(){
//								// loading start, avoid choking when loading content and animation happen at the same time
//								_this.startLoading = true; 
//								self.call(_this);
//							});
//							break;
//						default:
//							self.call(_this);
//							break;
//					}
//				});
//				this.extend('close', function(self){
//					switch(this.opts.anim){
//						case 0:
//							this.mainWrap.animate({
//								top: (this.mainWrap.offset().top - 40) + 'px',
//								opacity: 0
//							}, function(){
//								self.call(_this);
//							});
//							break;
//						case 1:
//							this.mainWrap.animate({
//								left: (this.mainWrap.offset().left - 80) + 'px',
//								opacity: 0
//							}, function(){
//								self.call(_this);
//							});
//							break;
//						case 2:
//							this.mainWrap.animate({
//								left: (this.mainWrap.offset().left + 80) + 'px',
//								opacity: 0
//							}, function(){
//								self.call(_this);
//							});
//							break;
//						default:
//							self.call(_this);
//							break;
//					}
//				});
//			}
//		}
//	$.msgbox.fixdimen = {//固定尺寸
//			domready: function(){
//				this.extend('show', function(self){
//					// need to show first, because some of the dimensions cannot be got if they are hidden.
//					self.apply(this, arguments);
//					// fix titleWrap width
//					var _this = this;
//					var interval = setInterval(function(){ // wait until the image loaded
//						if( _this.closeWrap.outerWidth(true) > 0 ){
//							clearInterval(interval);
//							_this.titleWrap.width( _this.opts.width - 
//												  _this.closeWrap.outerWidth(true) - 
//												  parseInt(_this.headWrap.css('padding-left')) -
//												  parseInt(_this.headWrap.css('padding-right')) -
//												  parseInt(_this.headWrap.css('border-right-width')) -
//												  parseInt(_this.headWrap.css('border-left-width')) -
//												  parseInt(_this.headWrap.css('margin-left')) -
//												  parseInt(_this.headWrap.css('margin-right')) - 5 );
//						}
//					}, 200);
//					// fix contentWrapWrap height
//					this.contentWrapWrap.height(this.opts.height - this.headWrap.outerHeight(true));
//				});
//			}
//		};
})(jQuery);