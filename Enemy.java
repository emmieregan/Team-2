public class Enemy {
    protected String name;
    protected int health;
    protected int baseDamage;

    public Enemy(String name, int health, int baseDamage) {
        this.name = name;
        this.health = health;
        this.baseDamage = baseDamage;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health < 0) {
            health = 0;
        }
    }

    public boolean isDefeated() {
        return health <= 0;
    }

    public int attack() {
        return baseDamage; // Default attack
    }

    @Override
    public String toString() {
        return name + " (HP: " + health + ")";
    }
}
