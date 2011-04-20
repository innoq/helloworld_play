#{list items:_statuses, as:'status'}
    <span class="profile-stream-item-date">
        ${status.createdAt.format()}<br />
    </span>
    ${status.message}<br />
#{/list}

