'use strict';

$.when($.ready).then(function () {
  var THEME_KEY = 'material-theme';
  var jekyllApp = {
    setBodyClass: function setBodyClass(css) {
      var classList = document.getElementsByTagName('body')[0].classList;

      classList.remove('oceanic', 'darker', 'lighter', 'palenight');
      classList.add('index', css);
    },
    setTheme: function setTheme(theme) {
      this.setBodyClass(theme.className);
    },
    showMenu: function showMenu() {
      $('.menu').toggleClass('active');
    },
    scrollSpyOn: function scrollSpyOn(wrapper, opt) {
      new window.ScrollSpy(wrapper, opt).init();
    },
    toggleSidebar: function toggleSidebar() {
      $('.sidebar').toggleClass('open');
    },
    init: function init() {
      // todo load from local storage
      var theme = localStorage.getItem(THEME_KEY) || 'oceanic';
      this.setBodyClass(theme);

      var $target = $('.doc');
      var $toc = $('.toc');

      if ($toc.length) {
        $toc.pushpin({
          top: 284,
          bottom: $target.offset().top + $target.outerHeight() - $toc.height(),
          offset: 64
        });
      }

      // custom scroll spy (is that necessary?)
      this.scrollSpyOn('.doc', { nav: '.toc a' });

      this.initMenu();
      this.initThemeChooser();
      this.initSidebar();
      // this.initSearch();
    },


    /**
     * Init Menu events
     */
    initMenu: function initMenu() {
      var _this = this;

      $('.js-menu-toggle').on('click touch', function (event) {
        _this.showMenu();
      });
    },


    /**
     * Init Theme Chooser events
     */
    initThemeChooser: function initThemeChooser() {
      var _this2 = this;

      $('.js-theme').on('click touch', function (event) {
        var $el = $(event.target);
        var themeClass = $el.data('themeClass');

        _this2.setBodyClass(themeClass);
        localStorage.setItem(THEME_KEY, themeClass);
      });
    },


    /**
     * Init Sidebar events
     */
    initSidebar: function initSidebar() {
      var _this3 = this;

      $('.js-sidebar-toggle').on('click touch', function (event) {
        _this3.toggleSidebar();
      });
    },
    initSearch: function initSearch() {
      var search = instantsearch({
        // Replace with your own values
        appId: '77GG697EBY',
        apiKey: 'b102643f245ea01e97b9a54047386402', // search only API key, no ADMIN key
        indexName: 'localhost',
        urlSync: true
      });

      search.addWidget(instantsearch.widgets.searchBox({
        container: '#search',
        magnifier: false,
        reset: false
      }));

      search.addWidget(instantsearch.widgets.hits({
        container: '#hits',
        hitsPerPage: 5,
        cssClasses: {
          root: 'search-results-hits',
          empty: 'search-results-empty',
          item: 'search-results-item'
        },
        templates: {
          item: '\n              <p>{{{_highlightResult.title.value}}}</p>\n              ',
          empty: 'We didn\'t find any results for the search <em>"{{query}}"</em>'
        }
      }));

      search.start();
    }
  };

  // init
  jekyllApp.init();

  window.jekyllApp = jekyllApp;

  //
  //
  // // lightbox stuff
  // $(function () {
  //   var activityIndicatorOn  = function () {
  //         $('<div id="imagelightbox-loading"><div></div></div>').appendTo('body');
  //       },
  //       activityIndicatorOff = function () {
  //         $('#imagelightbox-loading').remove();
  //       },
  //
  //       // OVERLAY
  //       overlayOn            = function () {
  //         $('<div id="imagelightbox-overlay"></div>').appendTo('body');
  //       },
  //       overlayOff           = function () {
  //         $('#imagelightbox-overlay').remove();
  //       },
  //
  //       // CLOSE BUTTON
  //       closeButtonOn        = function (instance) {
  //         $('<button type="button" id="imagelightbox-close" title="Close"><i class="fa
  // fa-times-circle"></i></button>') .appendTo('body') .on('click touchend', function () { $(this).remove();
  // instance.quitImageLightbox(); return false; }); }, closeButtonOff       = function () {
  // $('#imagelightbox-close').remove(); },  // CAPTION captionOn            = function () { var description =
  // $('a[href="' + $('#imagelightbox').attr('src') + '"] img').attr('alt'); if (description.length > 0) { $('<div
  // id="imagelightbox-caption">' + description + '</div>').appendTo('body'); } }, captionOff           = function () {
  // $('#imagelightbox-caption').remove(); },  // NAVIGATION navigationOn         = function (instance, selector) { var
  // images = $(selector); if (images.length) { var nav = $('<div id="imagelightbox-nav"></div>'); for (var i = 0; i <
  // images.length; i++) nav.append('<button type="button"></button>'); nav.appendTo('body'); nav.on('click touchend',
  // function () { return false; }); var navItems = nav.find('button'); navItems.on('click touchend', function () { var
  // $this = $(this); if (images.eq($this.index()).attr('href') != $('#imagelightbox').attr('src')) {
  // instance.switchImageLightbox($this.index()); } navItems.removeClass('active');
  // navItems.eq($this.index()).addClass('active'); return false; }) .on('touchend', function () { return false; }); }
  // }, navigationUpdate     = function (selector) { var items = $('#imagelightbox-nav button');
  // items.removeClass('active'); items.eq($(selector).filter('[href="' + $('#imagelightbox').attr('src') +
  // '"]').index(selector)) .addClass('active'); }, navigationOff        = function () {
  // $('#imagelightbox-nav').remove(); },  // ARROWS arrowsOn             = function (instance, selector) { var $arrows
  // = $('<button type="button" class="imagelightbox-arrow imagelightbox-arrow-left"></button><button type="button"
  // class="imagelightbox-arrow imagelightbox-arrow-right"></button>'); $arrows.appendTo('body'); $arrows.on('click
  // touchend', function (e) { e.preventDefault(); var $this   = $(this), $target = $(selector + '[href="' +
  // $('#imagelightbox').attr('src') + '"]'), index   = $target.index(selector);  if
  // ($this.hasClass('imagelightbox-arrow-left')) { index = index - 1; if (!$(selector).eq(index).length) { index =
  // $(selector).length; } } else { index = index + 1; if (!$(selector).eq(index).length) { index = 0; } }
  // instance.switchImageLightbox(index); return false; }); }, arrowsOff            = function () {
  // $('.imagelightbox-arrow').remove(); };  //	ALL COMBINED var selectorF = 'a[data-imagelightbox="f"]';  var
  // instanceF = $(selectorF).imageLightbox({ onStart: function () { overlayOn(); closeButtonOn(instanceF);
  // arrowsOn(instanceF, selectorF); }, onEnd: function () { overlayOff(); captionOff(); closeButtonOff(); arrowsOff();
  // activityIndicatorOff(); }, onLoadStart: function () { captionOff(); activityIndicatorOn(); }, onLoadEnd: function
  // () { captionOn(); activityIndicatorOff(); $('.imagelightbox-arrow').css('display', 'block'); }, });
});
//# sourceMappingURL=app.js.map
