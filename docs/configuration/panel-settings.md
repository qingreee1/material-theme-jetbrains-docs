---
layout: docs
title: Panel Settings
description: Appearance Settings for the whole application.
group: configuration
toc: true

previous:
  url: '/docs/configuration/tab-settings'
  title: Tab Settings
next:
  url: '/docs/configuration/component-settings'
  title: Component Settings

colors:
  - name: default
    color: '#1E272C'
    light: false
  - name: darker
    color: '#1A1A1A'
    light: false
  - name: lighter
    color: '#F4F4F4'
    light: true
  - name: palenight
    color: '#202331'
    light: false
---

This page presents the settings that controls the IDE's panels' appearance.
{:class='title'}

### Contrast Mode

The contrast mode allows you to add more contrast between the IDE's panels and components for better visualizing.

{% include figure.html content="/screens/noContrast.png" caption="Without Contrast enabled" %}
{% include figure.html content="/screens/withContrast.png" caption="With Contrast enabled" %}

The parts that are affected by the contrast change are:
- The project tree
- The settings tree
- Inactive tabs
- Input, Search and number controls
- Dropdowns
- Lists and Tables

#### Color Palette

{% for color in page.colors %}
{% include color.html color=color %}
{% endfor %}

----
### Hide File Icons

This option allows you to _completely hide_ the file icons from the IDE. This means from the _Project Tree_, Tabs, Dialogs and everything. This does not remove folders, though.

{% include figure.html content="/screens/hideFileIcons.png" caption="Hide File Icons" %}

This feature depends on the [Material File Icons]({{ site.baseurl }}/docs/configuration/component-settings#material-file-icons)

**Note**: This feature takes precedence over default file icons providers and the plugin's own one. However some plugins, such as the _PHP_ or _Python_ plugins, may have an even greater precedence, meaning that you will still see icons from these plugins.
{:class='card-panel'}

----
