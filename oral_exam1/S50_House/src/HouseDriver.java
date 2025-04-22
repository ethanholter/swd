public class HouseDriver {
    public static void main(String[] args) {
        House myHouse = new House(400000);
        Person me = new Person("Ethan Holter");
        Door myFrontDoor = new Door("pink");
        SmallApartment myApt = new SmallApartment();
        myApt.setFloor(42);
        myHouse.setPerson(me);
        myHouse.setDoor(myFrontDoor);

        System.out.println(myHouse);
        System.out.println(myHouse.getDoor());
        System.out.println(myHouse.getPerson());
        System.out.println(myApt);
        System.out.println(myApt.getFloor());

    }

}
