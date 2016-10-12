/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment1;
import java.util.*;
import java.io.IOException;
import java.math.*;


/**
 *
 * @author c3165020
 */
public class Clustering {
    LinkedList<Hotspot> hList;
    LinkedList<Edge> edgeList;
    Scanner sc = new Scanner(System.in);
    
    public Clustering(){
        hList = new LinkedList<>();
        edgeList = new LinkedList<>();
    }
    
    public void run(){
        calcEdgeweights(hList.size());
        System.out.println("Hello and welcome to Kruskal's Clustering!");
        System.out.println();
        System.out.println("There are "+hList.size()+" hotspots.");
        System.out.println();
        int i=1;
        while(i!=0){
            System.out.println("How many emergency stations would you like?");
            System.out.println("(Enter a number between 1 and "+hList.size()+" to "
                    + "place the emergency stations.");
            System.out.println("Enter 0 to exit.");
            System.out.println();
            i = sc.nextInt();
            if(i>0&&i<=hList.size()){
                kruskalAlg(i);
            }else if(i==0){
                System.out.println("Thankyou for using Kruskal's Clustering. Bye.");
                break;
            }else{
                System.out.println("Entry not valid.");
            }                    
        }     
    }
    
    public boolean listAdd(Hotspot spot){
        return hList.add(spot);
    }
    
    private void kruskalAlg(int i){
        //initially place each hotspot into it's own set
        LinkedList<HashSet<Hotspot>> clusters = new LinkedList<>();
        for(int j=0;j<hList.size();j++){
            clusters.add(new HashSet<>());
            clusters.get(j).add(hList.get(j));
        }
        int count = hList.size();
        while(count>i){
            Edge edge = edgeList.pop();

            int s1 = findSet(edge.getV1(), clusters);
            int s2 = findSet(edge.getV2(), clusters);

            if(s1!=s2){
                //union the two sets
                clusters.get(s1).addAll(clusters.get(s2));
                clusters.remove(s2);
            }
            //pop the next edge
            //if one of the verts of the edge is in 'cluster' -> add to cluster
            count--;
        }
        edgeList.clear();
        int index = 0;
        for(HashSet c:clusters){
            index++;
            System.out.println("Station: "+index);
            Hotspot centroid = calcCentroid(c, index);
            System.out.println("Coordinates: ("+centroid.getX()+", "+centroid.getY()+")");
            System.out.print("Hotspots: {"+this.clusterNames(c)+"}");
            System.out.println();
        }
        //find the interclustering distance
        
    }
    private double interClustering(){
        //calculate the edgeweights between the hotspots of each cluster
        //Edge e = new Edge(hList.get(i),hList.get(j));
        return 5.0;
    }
    
    private Hotspot calcCentroid(HashSet<Hotspot> set, int i){
        Iterator iterator = set.iterator();
        int xTotal = 0;
        int yTotal = 0;
        int count = set.size();
        while(iterator.hasNext()){
            Hotspot h = (Hotspot)iterator.next();
            xTotal = xTotal+h.getX();
            yTotal = yTotal+h.getY();
        }
        Hotspot centroid = new Hotspot(xTotal/count, yTotal/count, "centroid "+i);
        
        return centroid;
    }
    
    private String clusterNames(HashSet<Hotspot> set){
        Iterator iterator = set.iterator();
        String names = "";
        while(iterator.hasNext()){
            Hotspot h = (Hotspot)iterator.next();
            names += h.getName()+",";
        }
        names = names.substring(0, names.length()-1);
        return names;
    }
    
    //returns the index of the set containing the passed in hotspot
    private int findSet(Hotspot h1, LinkedList<HashSet<Hotspot>> set){
        int index = 0;
        for (int i=0; i<set.size();i++){
            if(set.get(i).contains(h1)){
                index = i;
            }
        }
        return index;
    }
    
    private void calcEdgeweights(int n){
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                Edge e = new Edge(hList.get(i),hList.get(j));
                e.setName(hList.get(i).getName()+"-"+hList.get(j).getName());
                int h = (hList.get(j).getX())-(hList.get(i).getX());
                int l = (hList.get(j).getY())-(hList.get(i).getY());
                double d = Math.sqrt(Math.pow(h,2)+Math.pow(l,2));
                BigDecimal bd = new BigDecimal(d);
                bd = bd.setScale(2, RoundingMode.HALF_UP);
                e.setWeight(bd.doubleValue());
                edgeList.add(e);
            }
        }
        Collections.sort(edgeList);
    }
}
