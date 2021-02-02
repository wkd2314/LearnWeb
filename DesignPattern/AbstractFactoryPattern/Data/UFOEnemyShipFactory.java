package AbstractFactoryPattern.Data;

public class UFOEnemyShipFactory implements EnemyShipFactory {

    public ESWeapon addESGUN() {
        return new ESUFOGun();
    }

    public ESEngine addEsEngine() {
        return new ESUFOEngine();
    }

}
