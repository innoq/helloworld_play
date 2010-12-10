#{extends 'main.html' /}
#{set title:'Status' /}

<div class="profile-stream-item">
  <span class="profile-stream-item-date"><%= l(status.created_at, :format => :short) %></span>
  <%= status.message %>
</div>
