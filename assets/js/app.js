'use strict';

$.when($.ready).then(function () {
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
      this.setBodyClass('oceanic');

      $('.toc').pushpin({
        top: 284,
        offset: 64
      });

      // custom scroll spy (is that necessary?)
      this.scrollSpyOn('.doc', { nav: '.toc a' });

      this.initSearch();
    },
    initSearch: function initSearch() {
      var search = instantsearch({
        // Replace with your own values
        appId: '77GG697EBY',
        apiKey: 'b102643f245ea01e97b9a54047386402', // search only API key, no ADMIN key
        indexName: 'localhost',
        urlSync: true,
      });

      search.addWidget(instantsearch.widgets.searchBox({
        container: '#searchInput',
      }));

      search.addWidget(instantsearch.widgets.hits({
        container: '#hits',
        hitsPerPage: 10,
        templates: {
          item: '{{{_highlightResult.title.value}}}',
          empty: 'We didn\'t find any results for the search <em>"{{query}}"</em>',
        }
      }));

      search.addWidget(instantsearch.widgets.pagination({
        container: '#pagination',
      }));

      search.start();
    },
    showSearch: function showSearch() {
      $('#search').addClass('open');
      $('#search input').focus();
      $('body').addClass('overflow');
    },
    closeSearch: function closeSearch(event, elt) {
      if (event.target === elt || event.target.className === 'close' || event.keyCode === 27) {
        $('#search').removeClass('open');
        $('body').removeClass('overflow');
      }
    }
  };

  // init
  jekyllApp.init();

  window.jekyllApp = jekyllApp;

  //
  //   // Back to top link
  //   $('.backtotop').click(function () {
  //     $('body,html').animate({
  //       scrollTop: 0,
  //     }, 500);
  //     return false;
  //   });
  //
  //   // Responsive Menu
  //   $('.toggle-link').click(function () {
  //     $('.menu').toggleClass('active');
  //   });
  //

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
