package nl.tudelft.fishy.factories;

public final class FactoryProducer {
  
  /**
   * The constructor.
   */
  private FactoryProducer() {
    
  }
  
  private static FactoryProducer factoryProducer;
  
  /**
   * Creates and gives the appropriate factory.
   * @param choice
   *        The factory needed.
   * @return
   *        The corresponding factory.
   */
  public AbstractFactory getFactory(String choice) {
   
    switch (choice.toUpperCase()) {
      case "ANIMATIONTIMER":
        return AnimationTimerFactory.getAnimationTimerFactory();
      case "ENTITY":
        return EntityFactory.getEntityFactory();
      case "ITEM":
        return ItemFactory.getItemFactory();
      case "LOSINGSCREEN":
        return LosingScreenEventHandlerFactory.getLosingScreenEHFactory();
      case "MAINSCREEN" :
        return MainScreenEventHandlerFactory.getMainScreenEHFactory();
      case "OPTIONSSCREEN" :
        return OptionsScreenEventHandlerFactory.getOptionsScreenEventHandlerFactory();
      case "WINNINGSCREEN" :
        return WinningScreenEventHandlerFactory.getWinningScreenEventHandlerFactory();
      default :
        return null;
    }
    
  }
  
  /**
   * Basic getter.
   * @return The singleton EntityFactory
   */
  public static synchronized FactoryProducer getEntityFactory() {
    
    if (factoryProducer == null) {
      factoryProducer = new FactoryProducer();
    }
    
    return factoryProducer;
  }

}
