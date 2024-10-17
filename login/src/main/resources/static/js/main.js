function toggleLocation() {
    const locationCheck = document.getElementById('locationCheck').checked;
    const locationSelect = document.getElementById('locationSelect');
    const resourceSection = document.getElementById('resourceSection');

    if (locationCheck) {
        locationSelect.style.display = 'block';
        resourceSection.style.display = 'none';
    } else {
        locationSelect.style.display = 'none';
        resourceSection.style.display = 'block';
    }
}

function toggleMenu() {
    const dropdownMenu = document.querySelector('.dropdown-menu');
    if (dropdownMenu.style.display === 'block') {
        dropdownMenu.style.display = 'none';
    } else {
        dropdownMenu.style.display = 'block';
    }
}

document.addEventListener('click', function(event) {
    const isClickInside = document.querySelector('.navbar-user').contains(event.target);
    if (!isClickInside) {
        document.querySelector('.dropdown-menu').style.display = 'none';
    }
});
