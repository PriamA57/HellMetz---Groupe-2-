 const eventDate = new Date(2027, 6, 7, 0, 0, 0).getTime();
    const countdown = document.getElementById("countdown");

    function updateCountdown() {
        const now = new Date().getTime();
        let diff = eventDate - now;

        if (diff <= 0) {
            countdown.textContent = "C'est parti !";
            return;
        }

        const days = Math.floor(diff / (1000 * 60 * 60 * 24));
        diff %= (1000 * 60 * 60 * 24);
        const hours = Math.floor(diff / (1000 * 60 * 60));
        diff %= (1000 * 60 * 60);
        const minutes = Math.floor(diff / (1000 * 60));
        const seconds = Math.floor((diff % (1000 * 60)) / 1000);

        countdown.textContent =
            `${days}j ${hours}h ${minutes}m ${seconds}s`;
    }

    updateCountdown();
    setInterval(updateCountdown, 1000);

    document.querySelectorAll('.artists-thumb').forEach(artist => {
  const popup = artist.querySelector('.artists-hover');
  const close = popup.querySelector('.artist-close');

  artist.addEventListener('click', () => {
    popup.classList.add('active');
  });

  close.addEventListener('click', e => {
    e.stopPropagation();
    popup.classList.remove('active');
  });

  popup.addEventListener('click', e => {
    if (e.target === popup) {
      popup.classList.remove('active');
    }
  });
});
