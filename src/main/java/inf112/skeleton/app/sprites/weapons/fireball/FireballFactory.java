package inf112.skeleton.app.sprites.weapons.fireball;

import inf112.skeleton.app.screens.PlayScreen;


public class FireballFactory {
    
        private PlayScreen screen;
    
        public FireballFactory(PlayScreen screen) {
            this.screen = screen;
        }
    
        public Fireball createFireball() {
            return new Fireball(screen);
        }
}
