package org.example;

import org.example.auction.Auction;
import org.example.auction.Bid;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println("Hello world!");
//        runAuction();
//        printClassModifiers(Class.forName("org.example.auction.Bid$Builder$BidImpl"));
//        printClassModifiers(Serializable.class);
//        printMethodsModifiers(Auction.class.getDeclaredMethods());
        printFieldsModifiers(Auction.class.getDeclaredFields());
    }

    public static void printFieldsModifiers(Field[] fields){
        for(Field field : fields) {
            int modifier = field.getModifiers();
            System.out.println(String.format("Field \"%s\" access modifier is %s",
                    field.getName(),
                    getAccessModifierName(modifier)));

            if(Modifier.isVolatile(modifier)) {
                System.out.println("The field is volatile");
            }

            if(Modifier.isFinal(modifier)){
                System.out.println("The field is final");
            }

            if(Modifier.isTransient(modifier)){
                System.out.println("The field is transient");
            }
            System.out.println();
        }
    }

    public static void printMethodsModifiers(Method[] methods){
        for(Method method: methods) {
            int modifier = method.getModifiers();

            System.out.println(String.format("%s() access modifier is %s",
                    method.getName(),
                    getAccessModifierName(modifier)));

            if(Modifier.isSynchronized(modifier)){
                System.out.println("The method is synchronized");
            } else {
                System.out.println("The method is not synchronized");
            }

            System.out.println();
        }
    }



    public static void printClassModifiers(Class<?> clazz) {
        int modifier = clazz.getModifiers();

        System.out.println(String.format("Class %s access modifier is %s",
                clazz.getSimpleName(),
                getAccessModifierName(modifier)));

        if(Modifier.isAbstract(modifier)){
            System.out.println("The class is abstract");
        }

        if(Modifier.isInterface(modifier)) {
            System.out.println("The class is interface");
        }
        if(Modifier.isStatic(modifier)) {
            System.out.println("The class is static");
        }
    }

    private static String getAccessModifierName(int modifier){
        if(Modifier.isPublic(modifier)) {
            return "public";
        }else if(Modifier.isPrivate(modifier)){
            return "private";
        }else if(Modifier.isProtected(modifier)){
            return "protected";
        } else{
            return "package-private";
        }
    }

    public static void runAuction(){
        Auction auction = new Auction();
        auction.startAuction();
        Bid bid1 = Bid.builder()
                .setBidderName("company1")
                .setPrice(10)
                .build();

        auction.addBid(bid1);

        Bid bid2 = Bid.builder()
                .setBidderName("company2")
                .setPrice(12)
                .build();

        auction.addBid(bid2);

        auction.stopAuction();

        System.out.println(auction.getAllBids());
        System.out.println("Highest bid :" + auction.getHighestBid().get());
    }
}