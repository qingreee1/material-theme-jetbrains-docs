'use strict';

$.when($.ready).then(function () {
  var THEME_KEY = 'material-theme';
  var themes = ['oceanic', 'darker', 'lighter', 'palenight', 'onedark', 'arcdark', 'monokai', 'solardark', 'solarlight'];

  var jekyllApp = {
    setBodyClass: function setBodyClass(css) {
      var classList = document.getElementsByTagName('body')[0].classList;

      classList.remove.apply(classList, themes);
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

      $(document).ready(function () {
        $('select').material_select();
      });

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
});
//# sourceMappingURL=app.js.map
