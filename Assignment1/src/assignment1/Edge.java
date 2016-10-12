/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment1;

/**
 *
 * @author c3165020 - Robert Logan
 */
public class Edge implements Comparable<Edge>{
    private Hotspot v;
    private Hotspot w;
    private double weight;
    private String name;
    
    public Edge(Hotspot a, Hotspot b){
        v = a;
        w = b;
        weight = 0;
        name = "";
    }
    
    public Hotspot getV1(){ return v; }
    
    public Hotspot getV2(){ return w; }
    
    public double getWeight(){ return weight; }
    
    public String getName(){ return name; } 
    
    public void setV1(Hotspot h){ v = h; }
    
    public void setY(Hotspot h){ w = h; }
    
    public void setWeight(double wt){ weight = wt; }
    
    public void setName(String n){ name = n; }
    
    @Override
    public int compareTo(Edge e2){
        return Double.compare(this.getWeight(),e2.getWeight());
    }

}
