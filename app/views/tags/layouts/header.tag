<div id="header">

    <div id="logo">
        <a href="@{Application.index()}"><img src="@{'/public/images/logo.png'}" alt="HelloWorld"/></a>
    </div>

    <div id="claim">
        <a href="@{Home.dashboard()}"><img src="@{'/public/images/claim.png'}" alt="The demo Community by innoQ"/></a>
    </div>

    <div id="meta">
        <ul>
            #{if user!=null }
            <li><a href="@{Auth.logout()}">Logout</a></li>
            #{/if}
            #{else}
            <li><a href="@{Auth.login()}">Login</a></li>
            #{/else}
            <li><a href="@{Home.imprint()}">Imprint</a></li>
            <li><a href="@{Home.about()}">About</a></li>
        </ul>
    </div>

    <div id="search">
        <form method="get" class="search" action="@{Search.search()}">
            <ul>
                <li>
                    <input type="text" name="q" class="search_term" value="Enter your value here" />
                </li>
                <li class="submit_search">
                    <input type="submit"  value="Search" />
                </li>
            </ul>
        </form>
    </div>

    <ul id="menu">
        <li class="first"><a href="@{Home.dashboard()}">Dashboard</a></li>
        <li><a href="@{Statuses.feed()}">World status feed</a></li>
        <li><a href="@{Contacts.index()}">Contacts</a></li>
        #{if user!=null}
           <li><a href="@{Profiles.show(user.profile)}">Profile</a></li>
        #{/if}
        #{else}
            <li><a href="@{Auth.login()}">Profile</a></li>
        #{/else}

        <li class="last"><a href="@{Home.about()}">About</a></li>
    </ul>

</div>
