/**
 * Représente un chemin et son coût dans le graphe.
 */
public class chemin {
    /**
     * Couut total du chemin
     */
    public final int cout;
    /**
     * Liste ordonnée des noeuds du chemin
     */
    public final java.util.List<Node> chemin;

    /**
     * Construit un chemin avec un coût et une liste de noeuds
     * 
     * @param cout   Coût total du chemin
     * @param chemin Liste des noeuds parcourus
     */
    public chemin(int cout, java.util.List<Node> chemin) {
        this.cout = cout;
        this.chemin = chemin;
    }
}