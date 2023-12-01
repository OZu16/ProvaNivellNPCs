package NPCs;

public class FullInventoryException extends Exception{

    public FullInventoryException(){
    }
    
    
    public String getMessage(){
        return "Inventario lleno! No se pueden añadir items.";
    }
}
