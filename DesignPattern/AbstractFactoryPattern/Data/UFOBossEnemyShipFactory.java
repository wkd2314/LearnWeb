package AbstractFactoryPattern.Data;

public class UFOBossEnemyShipFactory implements EnemyShipFactory {
    public ESWeapon addESGUN() {
        return new ESUFOBossGun();
    }

    public ESEngine addEsEngine() {
        return new ESUFOBossEngine();
    }

}
