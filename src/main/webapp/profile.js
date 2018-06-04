function onProfileLoad(user) {
    clearMessages();
    showContents(['profile-content', 'logout-content']);

    const userIdSpandEl = document.getElementById('user-id');
    const userCompanyNameSpandEl = document.getElementById('user-companyname');
    const userContactNameSpandEl = document.getElementById('user-contactname');

    userIdSpandEl.textContent = user.id;
    userCompanyNameSpandEl.textContent = user.companyName;
    userContactNameSpandEl.textContent = user.contactName;

}
