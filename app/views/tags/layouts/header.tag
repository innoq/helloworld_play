<div id="header">

    <div id="logo">
        #{a @Application.index()}<img src="@{'/public/images/logo.png'}" alt="HelloWorld"/>#{/a}
    </div>

    <div id="claim">
        #{a @Home.dashboard()}<img src="@{'/public/images/claim.png'}" alt="The demo Community by innoQ"/>#{/a}
    </div>

    <div id="meta">
        <ul>
            #{if user!=null }
            <li>#{a @Auth.logout()}Logout#{/a}</li>
            #{/if}
            #{else}
            <li>#{a @Auth.login()}Login#{/a}</li>
            #{/else}
            <li>#{a @Home.imprint()}Imprint#{/a}</li>
            <li>#{a @Home.about()}About#{/a}</li>
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
        <li class="first">#{a @Home.dashboard()}Dashboard#{/a}</li>
        <li>#{a @Statuses.form()}World status feed#{/a}</li>
        <li>#{a @Contacts.index()}Contacts#{/a}</li>
        <li>
            #{if user!=null}
                #{a @Profiles.privat(user.profile)}Profile#{/a}
            #{/if}
            #{else}
                #{a @Auth.login()}Profile#{/a}
            #{/else}     
        </li>
        <li class="last">#{a @Home.about()}About#{/a}</li>
    </ul>
        
</div>
