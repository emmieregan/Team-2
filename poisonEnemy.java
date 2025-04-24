
public class poisonEnemy extends Enemy {
    public poisonEnemy(String name, int health, int baseDamage) {
        super(name, health, baseDamage);
    }

    @Override
    public int attack() {
        return baseDamage + 1;
    }

    // Apply poison to player
    public void poisonPlayer(Player player) {
        player.setStatusEffect("Poisoned");
    }

    @Override
    public String toString() {
        return name + " (HP: " + health + ", Type: Poison)";
    }
}
