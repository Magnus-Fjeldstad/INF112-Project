package inf112.skeleton.app.tools.sound;

public class SoundManager {
    public final aSound fireball_shoot;
    public final aSound enemy_hit;
    public final aSound death;

    public SoundManager() {
        this.fireball_shoot = new aSound("sounds/fireball_shoot.ogg");
        this.enemy_hit = new aSound("sounds/enemy_hit.ogg");
        this.death = new aSound("sounds/death.ogg");
    }
}