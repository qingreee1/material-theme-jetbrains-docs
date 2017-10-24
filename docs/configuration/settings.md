---
layout: docs
title: Settings
description: Learn how to configure the plugin to suit your style
group: configuration
toc: true
---

The Material Theme plugin comes with a bunch of features tailored for an optimal experience. However, it is also highly configurable to
allow each and everyone to customize to their hearts content.
{:class='title'}

## Themes and Color Schemes

### Theme Switcher

The plugin comes prebundled with a set of themes interchangeables through the
![Theme Switcher]({{ site.media_folder | prepend: site.baseurl | replace: '//', '/' }}/icons/switcher.png) *Theme Switcher*.

There are many ways to invoke the *Theme Switcher*:
- From the IDE menu `Tools -> Material Theme -> Material Theme Chooser`
- From the _Main Toolbar_
- From the `Search Everything` dialog, type `Material Theme`
- From the `Quick Switch` panel <kbd>(Ctrl + \`)</kbd> (Windows: <kbd>Ctrl + ~</kbd>)`

![Theme Switcher]({{ site.media_folder | prepend: site.baseurl | replace: '//', '/' }}/screens/quickswitch.png)

The plugin comes prebundled with {{ site.data.themes.material.size | plus: site.data.themes.other.size }} themes:

#### Material Themes

These themes are based on the Sublime plugin's, made by Mattia Astorino ([@equinusocio](https://github.com/equinusocio))

<ul class="theme-cards">
{% for theme in site.data.themes.material %}
  {% include theme-card.html %}
{% endfor %}
</ul>

#### Other themes

Besides the Material Themes, there are also other prebundled themes made by the community:

<ul class="theme-cards">
{% for theme in site.data.themes.other %}
  {% include theme-card.html %}
{% endfor %}
</ul>

#### Custom Theme

And finally there is a **Custom Theme** option, which is the placeholder for the Custom Theme Settings, allowing you
to change the theme's colors to your convenience.

See [Custom Themes](/docs/custom-themes) for more information.

----------
## Material Theme Settings

You can configure many parts of the plugin features from the **plugin settings**. They are accessible within `Settings
-> Appearance -> Material Theme`.

![Material Theme Settings]({{ site.media_folder | prepend: site.baseurl | replace: '//', '/' }}/screens/settings.png)


{%comment%}
## Material Custom Theme Settings

## Troubleshooting
{%endcomment%}
