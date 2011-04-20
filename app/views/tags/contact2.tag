<div class="contact-list-small-item">
    <div class="contact-list-small-img">
        *{<%= link_to image_tag(contact.photo.url() , :border=>0), contact %>}*
        <a href="/profiles/show/${contact.source.id}"><img src="/public/${contact.source.getPhotoFileName()}" class="stream-image" alt="${contact.source.getFullName()}" border="0"/></a>
    </div>
    <div class="contact-list-small-text">
        <div class="contact-list-small-username">
            *{<%= link_to(contact.full_name, contact) %>}*
            <a href="/profiles/show/${contact.source.id}">${contact.source.getFullName()}</a>
        </div>
        <div class="contact-list-small-title">
            *{<%= contact.company %>}*
            ${contact.source.company}
        </div>
    </div>
    <div class="clear"><!-- clear --></div>
</div>

