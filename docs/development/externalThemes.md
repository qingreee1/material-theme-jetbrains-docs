---
layout: docs
title: External Themes
description: Create and Publish your Themes for the plugin
group: development
toc: true
comments: true

previous:
  url: '/docs/development/installation'
  title: Installation

next:
  url: '/docs/changelog'
  title: Changelog
---

In this section you will learn how to create your own external themes and distribute them via a plugin.
{:class='title'}

{% include carbonads.html %}

## Plugin Development

First of all to create and distribute your own plugin, you will need the Plugin DevKit. Please refer to the [Getting Started section]({{site.baseurl}}/docs/development/installation#getting-started) to download necessary dependencies.




----
## Environment files

Here are the important files necessary for plugin development.

### plugin.xml

The `plugin.xml` file is the core of the plugin. This is a _Manifest_ that describes the plugin and its components, and is the most important file of any plugin.

It is located inside `src/resources/META-INF` and should not be moved in any circumstances.

You can read more about plugin.xml [here](https://www.jetbrains.org/intellij/sdk/docs/basics/plugin_structure/plugin_configuration_file.html).

### build.gradle

The `build.gradle` file is the Gradle configuration used for making the plugin. It contains the dependencies required by the project, the different tasks that can be run (such as Tests or CheckStyle) and configuration for the Gradle Jetbrains plugin.

### gradle.properties

Last but not least, the `gradle.properties` file is a list of variables that can be used inside the `build.gradle` file. It can be useful to set different values without modifying the build.gradle file, or to use it on different development environments.

This file should not be commited to the Version Control.

----
## Documentation

To find resources about Plugin Development, first you can check at the [official documentation](https://www.jetbrains.org/intellij/sdk/docs/welcome.html). This will answer all questions regarding how to run the plugin locally.

But this documentation is pretty scarce, it's just the tip of the iceberg. The best way to learn is to actually look at the source code directly.

You can do that either by looking at the sources from GitHub or UpSource, or you can simply download the sources locally using Gradle. Simply open the tool window at the right and click on the `Refresh Project` to import the SDK and the sources.

You can also check out other plugins made by other developers to find inspiration.
