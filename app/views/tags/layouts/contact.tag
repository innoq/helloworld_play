<div class="contact-list-item">
    *{<%= link_to (image_tag contact.photo.url(), :alt => contact.full_name, :class => "user-image"), private_profile_url(contact) %>}*
    <a href="/profiles/show/${contact.source.id}"><img src="/public/${contact.source.getPhotoFileName()}" class="stream-image" alt="${contact.source.getFullName()}"/></a>
    <div class="contact-list-item-data">

        <h3>*{<%= link_to contact.full_name, private_profile_url(contact)  %>}*
            <a href="/profiles/show/${contact.source.id}">${contact.source.getFullName()}</a></h3>

        <p class="job-title">
            *{<%= contact.profession %>}*
            ${contact.source.profession}
        </p>

        <div class="address1">
            *{<%= contact.company %>}*
            ${contact.source.company}<br />
            *{<%= "{contact.business_address.zip} {contact.business_address.city}" if contact.business_address  %>}*
            #{ifnot contact.source.businessAddress == null }
            ${contact.source.businessAddress.zip} ${contact.source.businessAddress.city}
            #{/ifnot}
        </div>

        <div class="address2">
            *{<%= mail_to contact.company_email.value if contact.company_email %>}*
            #{list items:contact.source.profileAttribute, as:'profileAttribute'}
            <a href="mailto:${profileAttribute.companyEmail}">${profileAttribute.companyEmail}</a><br />
            #{/list}
            *{<%= contact.company_phone.value %>}*
            #{list items:contact.source.profileAttribute, as:'profileAttribute'}
            ${profileAttribute.companyPhone}<br />
            #{/list}
        </div>

        <div class="actions">
            *{<%- if current_user -%>
            <%#= link_to "Send message", new_message_url(:to => contact.id), :class => "message showmessage" %>
            <!-- <a class="export" href="#">Vcard Export</a>-->
            <%- end -%>}*

            *{<div class="contact-icon-holder">
                <%- if relation = current_user.profile.relations.where(:destination_id => contact.id).last -%>
                <% if relation.accepted %>
                <%= form_tag contact_url(contact.id), :method => :delete do %>
                <%= image_submit_tag 'contact-icon-remove.png', :title => "Remove contact", :class => "delete contact-icon", :confirm => "Sure?" %>
                <%- end -%>
                <%- else -%>
                <%= form_tag contact_url(contact.id), :method => :put do %>
                <%= hidden_field_tag(:accepted, true) %>
                <%= image_submit_tag 'contact-icon-add.png', :title => "Accept", :class => "accept contact-icon" %>
                <%- end -%>
                <%= form_tag contact_url(contact.id), :method => :delete do %>
                <%= image_submit_tag 'contact-icon-block.png', :title => "Reject", :class => "delete contact-icon", :confirm => "Sure?" %>
                <%- end -%>
                <%- end -%>
                <%- else -%>
                <%= form_tag contacts_url, :method => :post do %>
                <%= hidden_field_tag(:destination_id, contact.id) %>
                <%= image_submit_tag 'contact-icon-add.png', :title => "Add to contact list", :class => "add contact-icon" %>
                <%- end -%>
                <%- end -%>
            </div>}*

        </div>

    </div>
</div>
