<!DOCTYPE html>
<html class="" lang="en">
<head prefix="og: http://ogp.me/ns#">
<meta charset="utf-8">
<meta content="IE=edge" http-equiv="X-UA-Compatible">
<meta content="object" property="og:type">
<meta content="GitLab" property="og:site_name">
<meta content="README.MD · master · Hasan Alp Zengin / MQTT_implementation" property="og:title">
<meta content="IoT project in Manipal Institute of Technology" property="og:description">
<meta content="https://assets.gitlab-static.net/assets/gitlab_logo-7ae504fe4f68fdebb3c2034e36621930cd36ea87924c11ff65dbcb8ed50dca58.png" property="og:image">
<meta content="64" property="og:image:width">
<meta content="64" property="og:image:height">
<meta content="https://gitlab.com/hasanalpzengin/MQTT_implementation/blob/master/README.MD" property="og:url">
<meta content="summary" property="twitter:card">
<meta content="README.MD · master · Hasan Alp Zengin / MQTT_implementation" property="twitter:title">
<meta content="IoT project in Manipal Institute of Technology" property="twitter:description">
<meta content="https://assets.gitlab-static.net/assets/gitlab_logo-7ae504fe4f68fdebb3c2034e36621930cd36ea87924c11ff65dbcb8ed50dca58.png" property="twitter:image">

<title>README.MD · master · Hasan Alp Zengin / MQTT_implementation · GitLab</title>
<meta content="IoT project in Manipal Institute of Technology" name="description">
<link rel="shortcut icon" type="image/png" href="https://assets.gitlab-static.net/assets/favicon-7901bd695fb93edb07975966062049829afb56cf11511236e61bcf425070e36e.png" id="favicon" data-original-href="https://assets.gitlab-static.net/assets/favicon-7901bd695fb93edb07975966062049829afb56cf11511236e61bcf425070e36e.png" />
<link rel="stylesheet" media="all" href="https://assets.gitlab-static.net/assets/application-d6c60a08a76fa06abb456f326abe397a5a2902cd7607da8389f69d2aff222469.css" />
<link rel="stylesheet" media="print" href="https://assets.gitlab-static.net/assets/print-c8ff536271f8974b8a9a5f75c0ca25d2b8c1dceb4cff3c01d1603862a0bdcbfc.css" />


<script>
//<![CDATA[
window.gon={};gon.api_version="v4";gon.default_avatar_url="https://assets.gitlab-static.net/assets/no_avatar-849f9c04a3a0d0cea2424ae97b27447dc64a7dbfae83c036c45b403392f0e8ba.png";gon.max_file_size=10;gon.asset_host="https://assets.gitlab-static.net";gon.webpack_public_path="https://assets.gitlab-static.net/assets/webpack/";gon.relative_url_root="";gon.shortcuts_path="/help/shortcuts";gon.user_color_scheme="white";gon.sentry_dsn="https://526a2f38a53d44e3a8e69bfa001d1e8b@sentry.gitlap.com/15";gon.gitlab_url="https://gitlab.com";gon.revision="74d8455";gon.gitlab_logo="https://assets.gitlab-static.net/assets/gitlab_logo-7ae504fe4f68fdebb3c2034e36621930cd36ea87924c11ff65dbcb8ed50dca58.png";gon.sprite_icons="https://gitlab.com/assets/icons-2cdb7e353a1dc8023f99b5aadd54b9788b754858e7b42bb689753f63a0d68206.svg";gon.sprite_file_icons="https://gitlab.com/assets/file_icons-7262fc6897e02f1ceaf8de43dc33afa5e4f9a2067f4f68ef77dcc87946575e9e.svg";gon.emoji_sprites_css_path="https://assets.gitlab-static.net/assets/emoji_sprites-289eccffb1183c188b630297431be837765d9ff4aed6130cf738586fb307c170.css";gon.test_env=false;gon.suggested_label_colors=["#0033CC","#428BCA","#44AD8E","#A8D695","#5CB85C","#69D100","#004E00","#34495E","#7F8C8D","#A295D6","#5843AD","#8E44AD","#FFECDB","#AD4363","#D10069","#CC0033","#FF0000","#D9534F","#D1D100","#F0AD4E","#AD8D43"];gon.current_user_id=2404406;gon.current_username="hasanalpzengin";gon.current_user_fullname="Hasan Alp Zengin";gon.current_user_avatar_url="/uploads/-/system/user/avatar/2404406/avatar.png";
//]]>
</script>

<script src="https://assets.gitlab-static.net/assets/webpack/runtime.b4333992.bundle.js" defer="defer"></script>
<script src="https://assets.gitlab-static.net/assets/webpack/main.847ad87c.chunk.js" defer="defer"></script>
<script src="https://assets.gitlab-static.net/assets/webpack/raven.5e247a14.chunk.js" defer="defer"></script>
<script src="https://assets.gitlab-static.net/assets/webpack/commons~pages.groups.epics.show~pages.groups.milestones.edit~pages.groups.milestones.new~pages.proje~72f9ad42.0611a2c9.chunk.js" defer="defer"></script>
<script src="https://assets.gitlab-static.net/assets/webpack/commons~pages.projects.blame.show~pages.projects.blob.edit~pages.projects.blob.new~pages.projects.bl~046fef08.0babeae4.chunk.js" defer="defer"></script>
<script src="https://assets.gitlab-static.net/assets/webpack/pages.projects.blob.show.738f1699.chunk.js" defer="defer"></script>
<script>
  window.uploads_path = "/hasanalpzengin/MQTT_implementation/uploads";
</script>

<meta name="csrf-param" content="authenticity_token" />
<meta name="csrf-token" content="KMqtKtrT1XY5Dfgq4jHo4eXsiBUrLpVslx1cVloaSoQyFmOh27oSPe4XuJM6oeeF2lOiXJHJcq7AEKYivbVbxA==" />
<meta content="origin-when-cross-origin" name="referrer">
<meta content="width=device-width, initial-scale=1, maximum-scale=1" name="viewport">
<meta content="#474D57" name="theme-color">
<link rel="apple-touch-icon" type="image/x-icon" href="https://assets.gitlab-static.net/assets/touch-icon-iphone-5a9cee0e8a51212e70b90c87c12f382c428870c0ff67d1eb034d884b78d2dae7.png" />
<link rel="apple-touch-icon" type="image/x-icon" href="https://assets.gitlab-static.net/assets/touch-icon-ipad-a6eec6aeb9da138e507593b464fdac213047e49d3093fc30e90d9a995df83ba3.png" sizes="76x76" />
<link rel="apple-touch-icon" type="image/x-icon" href="https://assets.gitlab-static.net/assets/touch-icon-iphone-retina-72e2aadf86513a56e050e7f0f2355deaa19cc17ed97bbe5147847f2748e5a3e3.png" sizes="120x120" />
<link rel="apple-touch-icon" type="image/x-icon" href="https://assets.gitlab-static.net/assets/touch-icon-ipad-retina-8ebe416f5313483d9c1bc772b5bbe03ecad52a54eba443e5215a22caed2a16a2.png" sizes="152x152" />
<link color="rgb(226, 67, 41)" href="https://assets.gitlab-static.net/assets/logo-d36b5212042cebc89b96df4bf6ac24e43db316143e89926c0db839ff694d2de4.svg" rel="mask-icon">
<meta content="https://assets.gitlab-static.net/assets/msapplication-tile-1196ec67452f618d39cdd85e2e3a542f76574c071051ae7effbfde01710eb17d.png" name="msapplication-TileImage">
<meta content="#30353E" name="msapplication-TileColor">



</head>

<body class="ui-indigo " data-find-file="/hasanalpzengin/MQTT_implementation/find_file/master" data-group="" data-page="projects:blob:show" data-project="MQTT_implementation">



<header class="navbar navbar-gitlab navbar-gitlab-new qa-navbar navbar-expand-sm">
<a class="sr-only gl-accessibility" href="#content-body" tabindex="1">Skip to content</a>
<div class="container-fluid">
<div class="header-content">
<div class="title-container">
<h1 class="title">
<a title="Dashboard" id="logo" href="/"><svg width="24" height="24" class="tanuki-logo" viewBox="0 0 36 36">
  <path class="tanuki-shape tanuki-left-ear" fill="#e24329" d="M2 14l9.38 9v-9l-4-12.28c-.205-.632-1.176-.632-1.38 0z"/>
  <path class="tanuki-shape tanuki-right-ear" fill="#e24329" d="M34 14l-9.38 9v-9l4-12.28c.205-.632 1.176-.632 1.38 0z"/>
  <path class="tanuki-shape tanuki-nose" fill="#e24329" d="M18,34.38 3,14 33,14 Z"/>
  <path class="tanuki-shape tanuki-left-eye" fill="#fc6d26" d="M18,34.38 11.38,14 2,14 6,25Z"/>
  <path class="tanuki-shape tanuki-right-eye" fill="#fc6d26" d="M18,34.38 24.62,14 34,14 30,25Z"/>
  <path class="tanuki-shape tanuki-left-cheek" fill="#fca326" d="M2 14L.1 20.16c-.18.565 0 1.2.5 1.56l17.42 12.66z"/>
  <path class="tanuki-shape tanuki-right-cheek" fill="#fca326" d="M34 14l1.9 6.16c.18.565 0 1.2-.5 1.56L18 34.38z"/>
</svg>

<span class="logo-text d-none d-sm-block">
<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 617 169"><path d="M315.26 2.97h-21.8l.1 162.5h88.3v-20.1h-66.5l-.1-142.4M465.89 136.95c-5.5 5.7-14.6 11.4-27 11.4-16.6 0-23.3-8.2-23.3-18.9 0-16.1 11.2-23.8 35-23.8 4.5 0 11.7.5 15.4 1.2v30.1h-.1m-22.6-98.5c-17.6 0-33.8 6.2-46.4 16.7l7.7 13.4c8.9-5.2 19.8-10.4 35.5-10.4 17.9 0 25.8 9.2 25.8 24.6v7.9c-3.5-.7-10.7-1.2-15.1-1.2-38.2 0-57.6 13.4-57.6 41.4 0 25.1 15.4 37.7 38.7 37.7 15.7 0 30.8-7.2 36-18.9l4 15.9h15.4v-83.2c-.1-26.3-11.5-43.9-44-43.9M557.63 149.1c-8.2 0-15.4-1-20.8-3.5V70.5c7.4-6.2 16.6-10.7 28.3-10.7 21.1 0 29.2 14.9 29.2 39 0 34.2-13.1 50.3-36.7 50.3m9.2-110.6c-19.5 0-30 13.3-30 13.3v-21l-.1-27.8h-21.3l.1 158.5c10.7 4.5 25.3 6.9 41.2 6.9 40.7 0 60.3-26 60.3-70.9-.1-35.5-18.2-59-50.2-59M77.9 20.6c19.3 0 31.8 6.4 39.9 12.9l9.4-16.3C114.5 6 97.3 0 78.9 0 32.5 0 0 28.3 0 85.4c0 59.8 35.1 83.1 75.2 83.1 20.1 0 37.2-4.7 48.4-9.4l-.5-63.9V75.1H63.6v20.1h38l.5 48.5c-5 2.5-13.6 4.5-25.3 4.5-32.2 0-53.8-20.3-53.8-63-.1-43.5 22.2-64.6 54.9-64.6M231.43 2.95h-21.3l.1 27.3v94.3c0 26.3 11.4 43.9 43.9 43.9 4.5 0 8.9-.4 13.1-1.2v-19.1c-3.1.5-6.4.7-9.9.7-17.9 0-25.8-9.2-25.8-24.6v-65h35.7v-17.8h-35.7l-.1-38.5M155.96 165.47h21.3v-124h-21.3v124M155.96 24.37h21.3V3.07h-21.3v21.3"/></svg>

</span>
</a></h1>
<ul class="list-unstyled navbar-sub-nav">
<li id="nav-projects-dropdown" class="home dropdown header-projects qa-projects-dropdown"><a data-toggle="dropdown" href="#">
Projects
<svg class=" caret-down"><use xlink:href="https://gitlab.com/assets/icons-2cdb7e353a1dc8023f99b5aadd54b9788b754858e7b42bb689753f63a0d68206.svg#angle-down"></use></svg>
</a>
<div class="dropdown-menu projects-dropdown-menu">
<div class="projects-dropdown-container">
<div class="project-dropdown-sidebar qa-projects-dropdown-sidebar">
<ul>
<li class=""><a class="qa-your-projects-link" href="/dashboard/projects">Your projects
</a></li><li class=""><a href="/dashboard/projects/starred">Starred projects
</a></li><li class=""><a href="/explore">Explore projects
</a></li></ul>
</div>
<div class="project-dropdown-content">
<div data-project-id="7166713" data-project-name="MQTT_implementation" data-project-namespace="Hasan Alp Zengin / MQTT_implementation" data-project-web-url="/hasanalpzengin/MQTT_implementation" data-user-name="hasanalpzengin" id="js-projects-dropdown"></div>
</div>
</div>

</div>
</li><li class="d-none d-sm-block"><a class="dashboard-shortcuts-groups qa-groups-link" title="Groups" href="/dashboard/groups">Groups
</a></li><li class="d-none d-lg-block d-xl-block"><a class="dashboard-shortcuts-activity" title="Activity" href="/dashboard/activity">Activity
</a></li><li class="d-none d-lg-block d-xl-block"><a class="dashboard-shortcuts-milestones" title="Milestones" href="/dashboard/milestones">Milestones
</a></li><li class="d-none d-lg-block d-xl-block"><a class="dashboard-shortcuts-snippets" title="Snippets" href="/dashboard/snippets">Snippets
</a></li><li class="header-more dropdown d-lg-none d-xl-none">
<a data-toggle="dropdown" href="#">
More
<svg class=" caret-down"><use xlink:href="https://gitlab.com/assets/icons-2cdb7e353a1dc8023f99b5aadd54b9788b754858e7b42bb689753f63a0d68206.svg#angle-down"></use></svg>
</a>
<div class="dropdown-menu">
<ul>
<li class="d-block d-sm-none"><a class="dashboard-shortcuts-groups" title="Groups" href="/dashboard/groups">Groups
</a></li><li class=""><a title="Activity" href="/dashboard/activity">Activity
</a></li><li class=""><a class="dashboard-shortcuts-milestones" title="Milestones" href="/dashboard/milestones">Milestones
</a></li><li class=""><a class="dashboard-shortcuts-snippets" title="Snippets" href="/dashboard/snippets">Snippets
</a></li></ul>
</div>
</li>
<li class="hidden">
<a title="Projects" class="dashboard-shortcuts-projects" href="/dashboard/projects">Projects
</a></li>
</ul>

</div>
<div class="navbar-collapse collapse">
<ul class="nav navbar-nav">
<li class="header-new dropdown">
<a class="header-new-dropdown-toggle has-tooltip qa-new-menu-toggle" title="New..." ref="tooltip" aria-label="New..." data-toggle="dropdown" data-placement="bottom" data-container="body" data-display="static" href="/projects/new"><svg class="s16"><use xlink:href="https://gitlab.com/assets/icons-2cdb7e353a1dc8023f99b5aadd54b9788b754858e7b42bb689753f63a0d68206.svg#plus-square"></use></svg>
<svg class=" caret-down"><use xlink:href="https://gitlab.com/assets/icons-2cdb7e353a1dc8023f99b5aadd54b9788b754858e7b42bb689753f63a0d68206.svg#angle-down"></use></svg>
</a><div class="dropdown-menu dropdown-menu-right">
<ul>
<li class="dropdown-bold-header">This project</li>
<li>
<a href="/hasanalpzengin/MQTT_implementation/issues/new">New issue</a>
</li>
<li>
<a href="/hasanalpzengin/MQTT_implementation/merge_requests/new">New merge request</a>
</li>
<li class="header-new-project-snippet">
<a href="/hasanalpzengin/MQTT_implementation/snippets/new">New snippet</a>
</li>
<li class="divider"></li>
<li class="dropdown-bold-header">GitLab</li>
<li>
<a href="/projects/new">New project</a>
</li>
<li>
<a href="/groups/new">New group</a>
</li>
<li>
<a href="/snippets/new">New snippet</a>
</li>
</ul>
</div>
</li>

<li class="nav-item d-none d-sm-none d-md-block">
<div class="has-location-badge search search-form">
<form class="form-inline" action="/search" accept-charset="UTF-8" method="get"><input name="utf8" type="hidden" value="&#x2713;" /><div class="search-input-container">
<div class="location-badge">This project</div>
<div class="search-input-wrap">
<div class="dropdown" data-url="/search/autocomplete">
<input type="search" name="search" id="search" placeholder="Search" class="search-input dropdown-menu-toggle no-outline js-search-dashboard-options" spellcheck="false" tabindex="1" autocomplete="off" data-issues-path="/dashboard/issues" data-mr-path="/dashboard/merge_requests" aria-label="Search" />
<button class="hidden js-dropdown-search-toggle" data-toggle="dropdown" type="button"></button>
<div class="dropdown-menu dropdown-select">
<div class="dropdown-content"><ul>
<li class="dropdown-menu-empty-item">
<a>
Loading...
</a>
</li>
</ul>
</div><div class="dropdown-loading"><i aria-hidden="true" data-hidden="true" class="fa fa-spinner fa-spin"></i></div>
</div>
<svg class="s16 search-icon"><use xlink:href="https://gitlab.com/assets/icons-2cdb7e353a1dc8023f99b5aadd54b9788b754858e7b42bb689753f63a0d68206.svg#search"></use></svg>
<svg class="s16 clear-icon js-clear-input"><use xlink:href="https://gitlab.com/assets/icons-2cdb7e353a1dc8023f99b5aadd54b9788b754858e7b42bb689753f63a0d68206.svg#close"></use></svg>
</div>
</div>
</div>
<input type="hidden" name="group_id" id="group_id" class="js-search-group-options" />
<input type="hidden" name="project_id" id="search_project_id" value="7166713" class="js-search-project-options" data-project-path="MQTT_implementation" data-name="MQTT_implementation" data-issues-path="/hasanalpzengin/MQTT_implementation/issues" data-mr-path="/hasanalpzengin/MQTT_implementation/merge_requests" data-issues-disabled="false" />
<input type="hidden" name="search_code" id="search_code" value="true" />
<input type="hidden" name="repository_ref" id="repository_ref" value="master" />

<div class="search-autocomplete-opts hide" data-autocomplete-path="/search/autocomplete" data-autocomplete-project-id="7166713" data-autocomplete-project-ref="master"></div>
</form></div>

</li>
<li class="nav-item d-inline-block d-sm-none d-md-none">
<a title="Search" aria-label="Search" data-toggle="tooltip" data-placement="bottom" data-container="body" href="/search"><svg class="s16"><use xlink:href="https://gitlab.com/assets/icons-2cdb7e353a1dc8023f99b5aadd54b9788b754858e7b42bb689753f63a0d68206.svg#search"></use></svg>
</a></li>
<li class="user-counter"><a title="Issues" class="dashboard-shortcuts-issues" aria-label="Issues" data-toggle="tooltip" data-placement="bottom" data-container="body" href="/dashboard/issues?assignee_id=2404406"><svg class="s16"><use xlink:href="https://gitlab.com/assets/icons-2cdb7e353a1dc8023f99b5aadd54b9788b754858e7b42bb689753f63a0d68206.svg#issues"></use></svg>
<span class="badge badge-pill hidden issues-count">
0
</span>
</a></li><li class="user-counter"><a title="Merge requests" class="dashboard-shortcuts-merge_requests" aria-label="Merge requests" data-toggle="tooltip" data-placement="bottom" data-container="body" href="/dashboard/merge_requests?assignee_id=2404406"><svg class="s16"><use xlink:href="https://gitlab.com/assets/icons-2cdb7e353a1dc8023f99b5aadd54b9788b754858e7b42bb689753f63a0d68206.svg#git-merge"></use></svg>
<span class="badge badge-pill hidden merge-requests-count">
0
</span>
</a></li><li class="user-counter"><a title="Todos" aria-label="Todos" class="shortcuts-todos" data-toggle="tooltip" data-placement="bottom" data-container="body" href="/dashboard/todos"><svg class="s16"><use xlink:href="https://gitlab.com/assets/icons-2cdb7e353a1dc8023f99b5aadd54b9788b754858e7b42bb689753f63a0d68206.svg#todo-done"></use></svg>
<span class="badge badge-pill hidden todos-count">
0
</span>
</a></li><li class="nav-item header-user dropdown">
<a class="header-user-dropdown-toggle" data-toggle="dropdown" href="/hasanalpzengin"><img width="23" height="23" class="header-user-avatar qa-user-avatar lazy" data-src="https://assets.gitlab-static.net/uploads/-/system/user/avatar/2404406/avatar.png" src="data:image/gif;base64,R0lGODlhAQABAAAAACH5BAEKAAEALAAAAAABAAEAAAICTAEAOw==" />
<svg class=" caret-down"><use xlink:href="https://gitlab.com/assets/icons-2cdb7e353a1dc8023f99b5aadd54b9788b754858e7b42bb689753f63a0d68206.svg#angle-down"></use></svg>
</a><div class="dropdown-menu dropdown-menu-right">
<ul>
<li class="current-user">
<div class="user-name bold">
Hasan Alp Zengin
</div>
@hasanalpzengin
</li>
<li class="divider"></li>
<li>
<a class="profile-link" data-user="hasanalpzengin" href="/hasanalpzengin">Profile</a>
</li>
<li>
<a href="/profile">Settings</a>
</li>
<li>
<a href="/help">Help</a>
</li>
<li class="divider"></li>
<li>
<a class="sign-out-link" href="/users/sign_out">Sign out</a>
</li>
</ul>

</div>
</li>
</ul>
</div>
<button class="navbar-toggler d-block d-sm-none" type="button">
<span class="sr-only">Toggle navigation</span>
<svg class="s12 more-icon js-navbar-toggle-right"><use xlink:href="https://gitlab.com/assets/icons-2cdb7e353a1dc8023f99b5aadd54b9788b754858e7b42bb689753f63a0d68206.svg#more"></use></svg>
<svg class="s12 close-icon js-navbar-toggle-left"><use xlink:href="https://gitlab.com/assets/icons-2cdb7e353a1dc8023f99b5aadd54b9788b754858e7b42bb689753f63a0d68206.svg#close"></use></svg>
</button>
</div>
</div>
</header>

<div class="layout-page page-with-contextual-sidebar">
<div class="nav-sidebar">
<div class="nav-sidebar-inner-scroll">
<div class="context-header">
<a title="MQTT_implementation" href="/hasanalpzengin/MQTT_implementation"><div class="avatar-container s40 project-avatar">
<div class="avatar s40 avatar-tile identicon" style="background-color: #F3E5F5; color: #555">M</div>
</div>
<div class="sidebar-context-title">
MQTT_implementation
</div>
</a></div>
<ul class="sidebar-top-level-items">
<li class="home"><a class="shortcuts-project" href="/hasanalpzengin/MQTT_implementation"><div class="nav-icon-container">
<svg><use xlink:href="https://gitlab.com/assets/icons-2cdb7e353a1dc8023f99b5aadd54b9788b754858e7b42bb689753f63a0d68206.svg#project"></use></svg>
</div>
<span class="nav-item-name">
Project
</span>
</a><ul class="sidebar-sub-level-items">
<li class="fly-out-top-item"><a href="/hasanalpzengin/MQTT_implementation"><strong class="fly-out-top-item-name">
Project
</strong>
</a></li><li class="divider fly-out-top-item"></li>
<li class=""><a title="Project details" class="shortcuts-project" href="/hasanalpzengin/MQTT_implementation"><span>Details</span>
</a></li><li class=""><a title="Activity" class="shortcuts-project-activity" href="/hasanalpzengin/MQTT_implementation/activity"><span>Activity</span>
</a></li><li class=""><a title="Cycle Analytics" class="shortcuts-project-cycle-analytics" href="/hasanalpzengin/MQTT_implementation/cycle_analytics"><span>Cycle Analytics</span>
</a></li></ul>
</li><li class="active"><a class="shortcuts-tree" href="/hasanalpzengin/MQTT_implementation/tree/master"><div class="nav-icon-container">
<svg><use xlink:href="https://gitlab.com/assets/icons-2cdb7e353a1dc8023f99b5aadd54b9788b754858e7b42bb689753f63a0d68206.svg#doc_text"></use></svg>
</div>
<span class="nav-item-name">
Repository
</span>
</a><ul class="sidebar-sub-level-items">
<li class="fly-out-top-item active"><a href="/hasanalpzengin/MQTT_implementation/tree/master"><strong class="fly-out-top-item-name">
Repository
</strong>
</a></li><li class="divider fly-out-top-item"></li>
<li class="active"><a href="/hasanalpzengin/MQTT_implementation/tree/master">Files
</a></li><li class=""><a href="/hasanalpzengin/MQTT_implementation/commits/master">Commits
</a></li><li class=""><a href="/hasanalpzengin/MQTT_implementation/branches">Branches
</a></li><li class=""><a href="/hasanalpzengin/MQTT_implementation/tags">Tags
</a></li><li class=""><a href="/hasanalpzengin/MQTT_implementation/graphs/master">Contributors
</a></li><li class=""><a href="/hasanalpzengin/MQTT_implementation/network/master">Graph
</a></li><li class=""><a href="/hasanalpzengin/MQTT_implementation/compare?from=master&amp;to=master">Compare
</a></li><li class=""><a href="/hasanalpzengin/MQTT_implementation/graphs/master/charts">Charts
</a></li><li class=""><a href="/hasanalpzengin/MQTT_implementation/path_locks">Locked Files
</a></li></ul>
</li><li class=""><a class="shortcuts-issues" href="/hasanalpzengin/MQTT_implementation/issues"><div class="nav-icon-container">
<svg><use xlink:href="https://gitlab.com/assets/icons-2cdb7e353a1dc8023f99b5aadd54b9788b754858e7b42bb689753f63a0d68206.svg#issues"></use></svg>
</div>
<span class="nav-item-name">
Issues
</span>
<span class="badge badge-pill count issue_counter">
0
</span>
</a><ul class="sidebar-sub-level-items">
<li class="fly-out-top-item"><a href="/hasanalpzengin/MQTT_implementation/issues"><strong class="fly-out-top-item-name">
Issues
</strong>
<span class="badge badge-pill count issue_counter fly-out-badge">
0
</span>
</a></li><li class="divider fly-out-top-item"></li>
<li class=""><a title="Issues" href="/hasanalpzengin/MQTT_implementation/issues"><span>
List
</span>
</a></li><li class=""><a title="Boards" href="/hasanalpzengin/MQTT_implementation/boards"><span>
Boards
</span>
</a></li><li class=""><a title="Labels" href="/hasanalpzengin/MQTT_implementation/labels"><span>
Labels
</span>
</a></li><li class=""><a title="Service Desk" href="/hasanalpzengin/MQTT_implementation/issues/service_desk"><span>Service Desk</span>
</a></li><li class=""><a title="Milestones" href="/hasanalpzengin/MQTT_implementation/milestones"><span>
Milestones
</span>
</a></li></ul>
</li><li class=""><a class="shortcuts-merge_requests" href="/hasanalpzengin/MQTT_implementation/merge_requests"><div class="nav-icon-container">
<svg><use xlink:href="https://gitlab.com/assets/icons-2cdb7e353a1dc8023f99b5aadd54b9788b754858e7b42bb689753f63a0d68206.svg#git-merge"></use></svg>
</div>
<span class="nav-item-name">
Merge Requests
</span>
<span class="badge badge-pill count merge_counter js-merge-counter">
0
</span>
</a><ul class="sidebar-sub-level-items is-fly-out-only">
<li class="fly-out-top-item"><a href="/hasanalpzengin/MQTT_implementation/merge_requests"><strong class="fly-out-top-item-name">
Merge Requests
</strong>
<span class="badge badge-pill count merge_counter js-merge-counter fly-out-badge">
0
</span>
</a></li></ul>
</li><li class=""><a class="shortcuts-pipelines" href="/hasanalpzengin/MQTT_implementation/pipelines"><div class="nav-icon-container">
<svg><use xlink:href="https://gitlab.com/assets/icons-2cdb7e353a1dc8023f99b5aadd54b9788b754858e7b42bb689753f63a0d68206.svg#rocket"></use></svg>
</div>
<span class="nav-item-name">
CI / CD
</span>
</a><ul class="sidebar-sub-level-items">
<li class="fly-out-top-item"><a href="/hasanalpzengin/MQTT_implementation/pipelines"><strong class="fly-out-top-item-name">
CI / CD
</strong>
</a></li><li class="divider fly-out-top-item"></li>
<li class=""><a title="Pipelines" class="shortcuts-pipelines" href="/hasanalpzengin/MQTT_implementation/pipelines"><span>
Pipelines
</span>
</a></li><li class=""><a title="Jobs" class="shortcuts-builds" href="/hasanalpzengin/MQTT_implementation/-/jobs"><span>
Jobs
</span>
</a></li><li class=""><a title="Schedules" class="shortcuts-builds" href="/hasanalpzengin/MQTT_implementation/pipeline_schedules"><span>
Schedules
</span>
</a></li><li class=""><a title="Charts" class="shortcuts-pipelines-charts" href="/hasanalpzengin/MQTT_implementation/pipelines/charts"><span>
Charts
</span>
</a></li></ul>
</li><li class=""><a class="shortcuts-operations" href="/hasanalpzengin/MQTT_implementation/environments"><div class="nav-icon-container">
<svg><use xlink:href="https://gitlab.com/assets/icons-2cdb7e353a1dc8023f99b5aadd54b9788b754858e7b42bb689753f63a0d68206.svg#cloud-gear"></use></svg>
</div>
<span class="nav-item-name">
Operations
</span>
</a><ul class="sidebar-sub-level-items">
<li class="fly-out-top-item"><a href="/hasanalpzengin/MQTT_implementation/environments"><strong class="fly-out-top-item-name">
Operations
</strong>
</a></li><li class="divider fly-out-top-item"></li>
<li class=""><a title="Environments" class="shortcuts-environments" href="/hasanalpzengin/MQTT_implementation/environments"><span>
Environments
</span>
</a></li><li class=""><a title="Kubernetes" class="shortcuts-kubernetes" href="/hasanalpzengin/MQTT_implementation/clusters"><span>
Kubernetes
</span>
<div class="feature-highlight js-feature-highlight" data-container="body" data-dismiss-endpoint="/-/user_callouts" data-highlight="gke_cluster_integration" data-placement="right" data-toggle="popover" data-trigger="manual" disabled></div>
</a><div class="feature-highlight-popover-content">
<img class="feature-highlight-illustration lazy" data-src="https://assets.gitlab-static.net/assets/illustrations/cluster_popover-9830388038d966d8d64d43576808f9d5ba05f639a78a40bae9a5ddc7cbf72f24.svg" src="data:image/gif;base64,R0lGODlhAQABAAAAACH5BAEKAAEALAAAAAABAAEAAAICTAEAOw==" />
<div class="feature-highlight-popover-sub-content">
<p>Allows you to add and manage Kubernetes clusters.</p>
<p>
Protip:
<a href="/help/topics/autodevops/index.md">Auto DevOps</a>
<span>uses Kubernetes clusters to deploy your code!</span>
</p>
<hr>
<button class="btn btn-create btn-sm dismiss-feature-highlight" type="button">
<span>Got it!</span>
<svg><use xlink:href="https://gitlab.com/assets/icons-2cdb7e353a1dc8023f99b5aadd54b9788b754858e7b42bb689753f63a0d68206.svg#thumb-up"></use></svg>
</button>
</div>
</div>
</li></ul>
</li><li class=""><a class="shortcuts-container-registry" href="/hasanalpzengin/MQTT_implementation/container_registry"><div class="nav-icon-container">
<svg><use xlink:href="https://gitlab.com/assets/icons-2cdb7e353a1dc8023f99b5aadd54b9788b754858e7b42bb689753f63a0d68206.svg#disk"></use></svg>
</div>
<span class="nav-item-name">
Registry
</span>
</a><ul class="sidebar-sub-level-items is-fly-out-only">
<li class="fly-out-top-item"><a href="/hasanalpzengin/MQTT_implementation/container_registry"><strong class="fly-out-top-item-name">
Registry
</strong>
</a></li></ul>
</li><li class=""><a class="shortcuts-wiki" href="/hasanalpzengin/MQTT_implementation/wikis/home"><div class="nav-icon-container">
<svg><use xlink:href="https://gitlab.com/assets/icons-2cdb7e353a1dc8023f99b5aadd54b9788b754858e7b42bb689753f63a0d68206.svg#book"></use></svg>
</div>
<span class="nav-item-name">
Wiki
</span>
</a><ul class="sidebar-sub-level-items is-fly-out-only">
<li class="fly-out-top-item"><a href="/hasanalpzengin/MQTT_implementation/wikis/home"><strong class="fly-out-top-item-name">
Wiki
</strong>
</a></li></ul>
</li><li class=""><a class="shortcuts-snippets" href="/hasanalpzengin/MQTT_implementation/snippets"><div class="nav-icon-container">
<svg><use xlink:href="https://gitlab.com/assets/icons-2cdb7e353a1dc8023f99b5aadd54b9788b754858e7b42bb689753f63a0d68206.svg#snippet"></use></svg>
</div>
<span class="nav-item-name">
Snippets
</span>
</a><ul class="sidebar-sub-level-items is-fly-out-only">
<li class="fly-out-top-item"><a href="/hasanalpzengin/MQTT_implementation/snippets"><strong class="fly-out-top-item-name">
Snippets
</strong>
</a></li></ul>
</li><li class=""><a class="shortcuts-tree" href="/hasanalpzengin/MQTT_implementation/edit"><div class="nav-icon-container">
<svg><use xlink:href="https://gitlab.com/assets/icons-2cdb7e353a1dc8023f99b5aadd54b9788b754858e7b42bb689753f63a0d68206.svg#settings"></use></svg>
</div>
<span class="nav-item-name qa-settings-item">
Settings
</span>
</a><ul class="sidebar-sub-level-items">
<li class="fly-out-top-item"><a href="/hasanalpzengin/MQTT_implementation/edit"><strong class="fly-out-top-item-name">
Settings
</strong>
</a></li><li class="divider fly-out-top-item"></li>
<li class=""><a title="General" href="/hasanalpzengin/MQTT_implementation/edit"><span>
General
</span>
</a></li><li class=""><a title="Members" href="/hasanalpzengin/MQTT_implementation/project_members"><span>
Members
</span>
</a></li><li class=""><a title="Badges" href="/hasanalpzengin/MQTT_implementation/settings/badges"><span>
Badges
</span>
</a></li><li class=""><a title="Integrations" href="/hasanalpzengin/MQTT_implementation/settings/integrations"><span>
Integrations
</span>
</a></li><li class=""><a title="Repository" href="/hasanalpzengin/MQTT_implementation/settings/repository"><span>
Repository
</span>
</a></li><li class=""><a title="CI / CD" href="/hasanalpzengin/MQTT_implementation/settings/ci_cd"><span>
CI / CD
</span>
</a></li><li class=""><a title="Pages" href="/hasanalpzengin/MQTT_implementation/pages"><span>
Pages
</span>
</a></li><li class=""><a title="Audit Events" href="/hasanalpzengin/MQTT_implementation/audit_events"><span>
Audit Events
</span>
</a></li>
</ul>
</li><a class="toggle-sidebar-button js-toggle-sidebar" role="button" title="Toggle sidebar" type="button">
<svg class=" icon-angle-double-left"><use xlink:href="https://gitlab.com/assets/icons-2cdb7e353a1dc8023f99b5aadd54b9788b754858e7b42bb689753f63a0d68206.svg#angle-double-left"></use></svg>
<svg class=" icon-angle-double-right"><use xlink:href="https://gitlab.com/assets/icons-2cdb7e353a1dc8023f99b5aadd54b9788b754858e7b42bb689753f63a0d68206.svg#angle-double-right"></use></svg>
<span class="collapse-text">Collapse sidebar</span>
</a>
<button name="button" type="button" class="close-nav-button"><svg class="s16"><use xlink:href="https://gitlab.com/assets/icons-2cdb7e353a1dc8023f99b5aadd54b9788b754858e7b42bb689753f63a0d68206.svg#close"></use></svg>
<span class="collapse-text">Close sidebar</span>
</button>
<li class="hidden">
<a title="Activity" class="shortcuts-project-activity" href="/hasanalpzengin/MQTT_implementation/activity"><span>
Activity
</span>
</a></li>
<li class="hidden">
<a title="Network" class="shortcuts-network" href="/hasanalpzengin/MQTT_implementation/network/master">Graph
</a></li>
<li class="hidden">
<a title="Charts" class="shortcuts-repository-charts" href="/hasanalpzengin/MQTT_implementation/graphs/master/charts">Charts
</a></li>
<li class="hidden">
<a class="shortcuts-new-issue" href="/hasanalpzengin/MQTT_implementation/issues/new">Create a new issue
</a></li>
<li class="hidden">
<a title="Jobs" class="shortcuts-builds" href="/hasanalpzengin/MQTT_implementation/-/jobs">Jobs
</a></li>
<li class="hidden">
<a title="Commits" class="shortcuts-commits" href="/hasanalpzengin/MQTT_implementation/commits/master">Commits
</a></li>
<li class="hidden">
<a title="Issue Boards" class="shortcuts-issue-boards" href="/hasanalpzengin/MQTT_implementation/boards">Issue Boards</a>
</li>
</ul>
</div>
</div>

<div class="content-wrapper">

<div class="mobile-overlay"></div>
<div class="alert-wrapper">





<nav class="breadcrumbs container-fluid container-limited" role="navigation">
<div class="breadcrumbs-container">
<button name="button" type="button" class="toggle-mobile-nav"><span class="sr-only">Open sidebar</span>
<i aria-hidden="true" data-hidden="true" class="fa fa-bars"></i>
</button><div class="breadcrumbs-links js-title-container">
<ul class="list-unstyled breadcrumbs-list js-breadcrumbs-list">
<li><a href="/hasanalpzengin">Hasan Alp Zengin</a><svg class="s8 breadcrumbs-list-angle"><use xlink:href="https://gitlab.com/assets/icons-2cdb7e353a1dc8023f99b5aadd54b9788b754858e7b42bb689753f63a0d68206.svg#angle-right"></use></svg></li> <li><a href="/hasanalpzengin/MQTT_implementation"><span class="breadcrumb-item-text js-breadcrumb-item-text">MQTT_implementation</span></a><svg class="s8 breadcrumbs-list-angle"><use xlink:href="https://gitlab.com/assets/icons-2cdb7e353a1dc8023f99b5aadd54b9788b754858e7b42bb689753f63a0d68206.svg#angle-right"></use></svg></li>

<li>
<h2 class="breadcrumbs-sub-title"><a href="/hasanalpzengin/MQTT_implementation/blob/master/README.MD">Repository</a></h2>
</li>
</ul>
</div>

</div>
</nav>

<div class="flash-container flash-container-page">
</div>

</div>
<div class=" ">
<div class="content" id="content-body">
<div class="container-fluid container-limited">

<div class="tree-holder" id="tree-holder">
<div class="nav-block">
<div class="tree-ref-container">
<div class="tree-ref-holder">
<form class="project-refs-form" action="/hasanalpzengin/MQTT_implementation/refs/switch" accept-charset="UTF-8" method="get"><input name="utf8" type="hidden" value="&#x2713;" /><input type="hidden" name="destination" id="destination" value="blob" />
<input type="hidden" name="path" id="path" value="README.MD" />
<div class="dropdown">
<button class="dropdown-menu-toggle js-project-refs-dropdown qa-branches-select" type="button" data-toggle="dropdown" data-selected="master" data-ref="master" data-refs-url="/hasanalpzengin/MQTT_implementation/refs?sort=updated_desc" data-field-name="ref" data-submit-form-on-click="true" data-visit="true"><span class="dropdown-toggle-text ">master</span><i aria-hidden="true" data-hidden="true" class="fa fa-chevron-down"></i></button>
<div class="dropdown-menu dropdown-menu-paging dropdown-menu-selectable git-revision-dropdown qa-branches-dropdown">
<div class="dropdown-page-one">
<div class="dropdown-title"><span>Switch branch/tag</span><button class="dropdown-title-button dropdown-menu-close" aria-label="Close" type="button"><i aria-hidden="true" data-hidden="true" class="fa fa-times dropdown-menu-close-icon"></i></button></div>
<div class="dropdown-input"><input type="search" id="" class="dropdown-input-field" placeholder="Search branches and tags" autocomplete="off" /><i aria-hidden="true" data-hidden="true" class="fa fa-search dropdown-input-search"></i><i role="button" aria-hidden="true" data-hidden="true" class="fa fa-times dropdown-input-clear js-dropdown-input-clear"></i></div>
<div class="dropdown-content"></div>
<div class="dropdown-loading"><i aria-hidden="true" data-hidden="true" class="fa fa-spinner fa-spin"></i></div>
</div>
</div>
</div>
</form>
</div>
<ul class="breadcrumb repo-breadcrumb">
<li class="breadcrumb-item">
<a href="/hasanalpzengin/MQTT_implementation/tree/master">MQTT_implementation
</a></li>
<li class="breadcrumb-item">
<a href="/hasanalpzengin/MQTT_implementation/blob/master/README.MD"><strong>README.MD</strong>
</a></li>
</ul>
</div>
<div class="tree-controls">
<a class="btn shortcuts-find-file" rel="nofollow" href="/hasanalpzengin/MQTT_implementation/find_file/master"><i aria-hidden="true" data-hidden="true" class="fa fa-search"></i>
<span>Find file</span>
</a>
<div class="btn-group" role="group"><a class="btn js-blob-blame-link" href="/hasanalpzengin/MQTT_implementation/blame/master/README.MD">Blame</a><a class="btn" href="/hasanalpzengin/MQTT_implementation/commits/master/README.MD">History</a><a class="btn js-data-file-blob-permalink-url" href="/hasanalpzengin/MQTT_implementation/blob/2d15c4e69b487898bcf2a29d42f81f952d852a43/README.MD">Permalink</a></div>
</div>
</div>

<div class="info-well d-none d-sm-block">
<div class="well-segment">
<ul class="blob-commit-info">
<li class="commit flex-row js-toggle-container" id="commit-efb1c412">
<div class="avatar-cell d-none d-sm-block">
<a href="/hasanalpzengin"><img alt="Hasan Alp Zengin&#39;s avatar" src="/uploads/-/system/user/avatar/2404406/avatar.png" data-container="body" class="avatar s36 d-none d-sm-inline has-tooltip" title="Hasan Alp Zengin" /></a>
</div>
<div class="commit-detail flex-list">
<div class="commit-content qa-commit-content">
<a class="commit-row-message item-title" href="/hasanalpzengin/MQTT_implementation/commit/efb1c4124be707d2d17965c30edf4d7886085b1e">README.MD Created</a>
<span class="commit-row-message d-block d-sm-none">
&middot;
efb1c412
</span>
<div class="commiter">
<a class="commit-author-link has-tooltip" title="hasalp38@gmail.com" href="/hasanalpzengin">Hasan Alp Zengin</a> authored <time class="js-timeago" title="Jun 27, 2018 1:09pm" datetime="2018-06-27T13:09:22Z" data-toggle="tooltip" data-placement="bottom" data-container="body">Jun 27, 2018</time>
</div>
</div>
<div class="commit-actions flex-row d-none d-sm-flex">

<div class="js-commit-pipeline-status" data-endpoint="/hasanalpzengin/MQTT_implementation/commit/efb1c4124be707d2d17965c30edf4d7886085b1e/pipelines?ref=master"></div>
<div class="commit-sha-group">
<div class="label label-monospace">
efb1c412
</div>
<button class="btn btn btn-default" data-toggle="tooltip" data-placement="bottom" data-container="body" data-title="Copy commit SHA to clipboard" data-class="btn btn-default" data-clipboard-text="efb1c4124be707d2d17965c30edf4d7886085b1e" type="button" title="Copy commit SHA to clipboard" aria-label="Copy commit SHA to clipboard"><i aria-hidden="true" aria-hidden="true" data-hidden="true" class="fa fa-clipboard"></i></button>

</div>
</div>
</div>
</li>

</ul>
</div>

</div>
<div class="blob-content-holder" id="blob-content-holder">
<article class="file-holder">
<div class="js-file-title file-title-flex-parent">
<div class="file-header-content">
<i aria-hidden="true" data-hidden="true" class="fa fa-file-text-o fa-fw"></i>
<strong class="file-title-name">
README.MD
</strong>
<button class="btn btn-clipboard btn-transparent prepend-left-5" data-toggle="tooltip" data-placement="bottom" data-container="body" data-class="btn-clipboard btn-transparent prepend-left-5" data-title="Copy file path to clipboard" data-clipboard-text="{&quot;text&quot;:&quot;README.MD&quot;,&quot;gfm&quot;:&quot;`README.MD`&quot;}" type="button" title="Copy file path to clipboard" aria-label="Copy file path to clipboard"><i aria-hidden="true" aria-hidden="true" data-hidden="true" class="fa fa-clipboard"></i></button>
<small>
381 Bytes
</small>
</div>

<div class="file-actions">
<div class="btn-group js-blob-viewer-switcher" role="group">
<button aria-label="Display source" class="btn btn-default btn-sm js-blob-viewer-switch-btn has-tooltip" data-container="body" data-viewer="simple" title="Display source">
<i aria-hidden="true" data-hidden="true" class="fa fa-code"></i>
</button><button aria-label="Display rendered file" class="btn btn-default btn-sm js-blob-viewer-switch-btn has-tooltip" data-container="body" data-viewer="rich" title="Display rendered file">
<i aria-hidden="true" data-hidden="true" class="fa fa-file-text-o"></i>
</button></div>

<div class="btn-group" role="group"><button class="btn btn btn-sm js-copy-blob-source-btn" data-toggle="tooltip" data-placement="bottom" data-container="body" data-class="btn btn-sm js-copy-blob-source-btn" data-title="Copy source to clipboard" data-clipboard-target=".blob-content[data-blob-id=&#39;e2a429a4092a274fb0f1bbdace7bbc829fc947de&#39;]" type="button" title="Copy source to clipboard" aria-label="Copy source to clipboard"><i aria-hidden="true" aria-hidden="true" data-hidden="true" class="fa fa-clipboard"></i></button><a class="btn btn-sm has-tooltip" target="_blank" rel="noopener noreferrer" title="Open raw" data-container="body" href="/hasanalpzengin/MQTT_implementation/raw/master/README.MD"><i aria-hidden="true" data-hidden="true" class="fa fa-file-code-o"></i></a></div>
<div class="btn-group" role="group"><a class="btn btn-sm path-lock has-tooltip" data-state="lock" data-toggle="tooltip" title="" href="#">Lock</a><a class="btn js-edit-blob  btn-sm" href="/hasanalpzengin/MQTT_implementation/edit/master/README.MD">Edit</a><a class="btn btn-default btn-sm" href="/-/ide/project/hasanalpzengin/MQTT_implementation/edit/master/-/README.MD">Web IDE</a><button name="button" type="submit" class="btn btn-default" data-target="#modal-upload-blob" data-toggle="modal">Replace</button><button name="button" type="submit" class="btn btn-remove" data-target="#modal-remove-blob" data-toggle="modal">Delete</button></div>
</div>
</div>
<div class="js-file-fork-suggestion-section file-fork-suggestion hidden">
<span class="file-fork-suggestion-note">
You're not allowed to
<span class="js-file-fork-suggestion-section-action">
edit
</span>
files in this project directly. Please fork this project,
make your changes there, and submit a merge request.
</span>
<a class="js-fork-suggestion-button btn btn-grouped btn-inverted btn-new" rel="nofollow" data-method="post" href="/hasanalpzengin/MQTT_implementation/blob/master/README.MD">Fork</a>
<button class="js-cancel-fork-suggestion-button btn btn-grouped" type="button">
Cancel
</button>
</div>

<script id="js-file-lock" type="application/json">
{"path":"README.MD","toggle_path":"/hasanalpzengin/MQTT_implementation/path_locks/toggle"}
</script>

<div class="blob-viewer hidden" data-type="simple" data-url="/hasanalpzengin/MQTT_implementation/blob/master/README.MD?format=json&amp;viewer=simple">
<div class="text-center prepend-top-default append-bottom-default">
<i aria-hidden="true" aria-label="Loading content…" class="fa fa-spinner fa-spin fa-2x"></i>
</div>

</div>

<div class="blob-viewer" data-rich-type="markup" data-type="rich" data-url="/hasanalpzengin/MQTT_implementation/blob/master/README.MD?format=json&amp;viewer=rich">
<div class="text-center prepend-top-default append-bottom-default">
<i aria-hidden="true" aria-label="Loading content…" class="fa fa-spinner fa-spin fa-2x"></i>
</div>

</div>


</article>
</div>

<div class="modal" id="modal-remove-blob">
<div class="modal-dialog">
<div class="modal-content">
<div class="modal-header">
<h3 class="page-title">Delete README.MD</h3>
<button aria-label="Close" class="close" data-dismiss="modal" type="button">
<span aria-hidden="true">&times;</span>
</button>
</div>
<div class="modal-body">
<form class="js-delete-blob-form js-quick-submit js-requires-input" action="/hasanalpzengin/MQTT_implementation/blob/master/README.MD" accept-charset="UTF-8" method="post"><input name="utf8" type="hidden" value="&#x2713;" /><input type="hidden" name="_method" value="delete" /><input type="hidden" name="authenticity_token" value="zkLNfxhUybyMcDgAR46XtUCk22aPOwjWgnlenRUG157UngP0GT0O91tqeLmfHpjRfxvxLzXc7xTVdKTp8qnG3g==" /><div class="form-group row commit_message-group">
<label class="col-form-label col-sm-2" for="commit_message-712992f958c6ae501a3e959a8d032aa0">Commit message
</label><div class="col-sm-10">
<div class="commit-message-container">
<div class="max-width-marker"></div>
<textarea name="commit_message" id="commit_message-712992f958c6ae501a3e959a8d032aa0" class="form-control js-commit-message" placeholder="Delete README.MD" required="required" rows="3">
Delete README.MD</textarea>
</div>
</div>
</div>

<div class="form-group row branch">
<label class="col-form-label col-sm-2" for="branch_name">Target Branch</label>
<div class="col-sm-10">
<input type="text" name="branch_name" id="branch_name" value="master" required="required" class="form-control js-branch-name ref-name" />
<div class="js-create-merge-request-container">
<div class="form-check prepend-top-8">
<input type="checkbox" name="create_merge_request" id="create_merge_request-87c1946a07df85089c79164db8c41e78" value="1" class="js-create-merge-request form-check-input" checked="checked" />
<label class="form-check-label" for="create_merge_request-87c1946a07df85089c79164db8c41e78">Start a <strong>new merge request</strong> with these changes
</label></div>

</div>
</div>
</div>
<input type="hidden" name="original_branch" id="original_branch" value="master" class="js-original-branch" />

<div class="form-group row">
<div class="offset-sm-2 col-sm-10">
<button name="button" type="submit" class="btn btn-remove btn-remove-file">Delete file</button>
<a class="btn btn-cancel" data-dismiss="modal" href="#">Cancel</a>
</div>
</div>
</form></div>
</div>
</div>
</div>

<div class="modal" id="modal-upload-blob">
<div class="modal-dialog modal-lg">
<div class="modal-content">
<div class="modal-header">
<h3 class="page-title">Replace README.MD</h3>
<button aria-label="Close" class="close" data-dismiss="modal" type="button">
<span aria-hidden="true">&times;</span>
</button>
</div>
<div class="modal-body">
<form class="js-quick-submit js-upload-blob-form" data-method="put" action="/hasanalpzengin/MQTT_implementation/update/master/README.MD" accept-charset="UTF-8" method="post"><input name="utf8" type="hidden" value="&#x2713;" /><input type="hidden" name="_method" value="put" /><input type="hidden" name="authenticity_token" value="6cruS1zRUGRQyFe1eOmTlbj7SSezA1CeMqhD0DaIwq7zFiDAXbiXL4fSFwygeZzxh0Rjbgnkt1xlpbmk0SfT7g==" /><div class="dropzone">
<div class="dropzone-previews blob-upload-dropzone-previews">
<p class="dz-message light">
Attach a file by drag &amp; drop or <a class="markdown-selector" href="#">click to upload</a>
</p>
</div>
</div>
<br>
<div class="dropzone-alerts alert alert-danger data" style="display:none"></div>
<div class="form-group row commit_message-group">
<label class="col-form-label col-sm-2" for="commit_message-d8b2a60c639742ca55948452870dea3a">Commit message
</label><div class="col-sm-10">
<div class="commit-message-container">
<div class="max-width-marker"></div>
<textarea name="commit_message" id="commit_message-d8b2a60c639742ca55948452870dea3a" class="form-control js-commit-message" placeholder="Replace README.MD" required="required" rows="3">
Replace README.MD</textarea>
</div>
</div>
</div>

<div class="form-group row branch">
<label class="col-form-label col-sm-2" for="branch_name">Target Branch</label>
<div class="col-sm-10">
<input type="text" name="branch_name" id="branch_name" value="master" required="required" class="form-control js-branch-name ref-name" />
<div class="js-create-merge-request-container">
<div class="form-check prepend-top-8">
<input type="checkbox" name="create_merge_request" id="create_merge_request-e2178485853f64c3d8fb8a02fd60c24b" value="1" class="js-create-merge-request form-check-input" checked="checked" />
<label class="form-check-label" for="create_merge_request-e2178485853f64c3d8fb8a02fd60c24b">Start a <strong>new merge request</strong> with these changes
</label></div>

</div>
</div>
</div>
<input type="hidden" name="original_branch" id="original_branch" value="master" class="js-original-branch" />

<div class="form-actions">
<button name="button" type="button" class="btn btn-create btn-upload-file" id="submit-all"><i aria-hidden="true" data-hidden="true" class="fa fa-spin fa-spinner js-loading-icon hidden"></i>
Replace file
</button><a class="btn btn-cancel" data-dismiss="modal" href="#">Cancel</a>

</div>
</form></div>
</div>
</div>
</div>

</div>
</div>

</div>
</div>
</div>
</div>



</body>
</html>

