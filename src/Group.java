import java.util.*;
import java.util.stream.Collectors;

public class Group {
    public List<Engine> engines;

    private Group() {}

    public Group(List<Engine> engines) {
        this.engines = new ArrayList<>(engines);
    }

    // Возвращает количество элементов
    public int getLenght() {
        return engines.size();
    }

    // возвращает лист элементов
    public List<Engine> getEngines() {
        return engines;
    }

    // Удаляет элемент по индексу
    public void deleteItem(int index) {
        engines.remove(index);
    }

    // Добавляет элемент
    public <T extends Engine> void addItem(T item) {
        engines.add(item);
    }



    // поиск по имени
    public Group searchName(String name) {
        Group res;
        res = new Group(engines.stream().filter(x -> x.getName().equals(name)).collect(Collectors.toList()));
        return res;
    }

    // поиск по мощности
    public Group searchPower(int power) {
        Group res;
        res = new Group(engines.stream().filter(x -> x.getPower() == power).collect(Collectors.toList()));
        return res;
    }

    // поиск по крутящему моменту
    public Group searchTotque(int torque) {
        Group res;
        res = new Group(engines.stream().filter(x -> x.getTorque() == torque).collect(Collectors.toList()));
        return res;
    }

    // поиск по наличию присадок
    public Group searchAdditives(boolean add) {
        Group res;
        res = new Group(pickEngineByClass(CombustionEngine.class, engines).stream().filter(
                x -> x.getAdditivies() == add).collect(Collectors.toList()));
        return res;
    }

    // поиск по плотности топлива
    public Group searchFuelDensity(int fuel_density) {
        Group res;
        res = new Group(pickEngineByClass(JetTutboEngine.class, engines).stream().filter(
                x -> x.getFuel_density() == fuel_density).collect(Collectors.toList()));
        return res;
    }

    // поиск по качеству топлева
    public Group searchFuelQuality(boolean fuel_quality) {
        Group res;
        res = new Group(pickEngineByClass(DieselEngine.class, engines).stream().filter(
                x -> x.getFuel_quality() == fuel_quality).collect(Collectors.toList()));
        return res;
    }

    // возвращает лист с указанными двигателями
    private static <T extends Engine> List<T> pickEngineByClass(Class<T> concreteClass, List<? extends Engine> engines) {
        return (List<T>) engines.stream()
                .filter(engine -> engine.getClass().equals(concreteClass))
                .collect(Collectors.toList());
    }






























//    // вывод информации о двигателях в консоль
//    public void printListEngin() {
//        engines.forEach(elem ->{
//            System.out.println("Name:   " + elem.getName());
//            System.out.println("Power:  " + elem.getPower());
//            System.out.println("Torque: " + elem.getTorque());
//            if( elem instanceof DieselEngine ){
//                System.out.println("Fuel quality: " + ((DieselEngine)elem).getFuel_quality());
//            } else if( elem instanceof JetTutboEngine ){
//                System.out.println("Fuel density: " + ((JetTutboEngine)elem).getFuel_density());
//            } else {
//                System.out.println("Additives: " + ((CombustionEngine)elem).getAdditivies());
//            }
//        });
//
////        takeEngines(engines);
//    }



//    // сортировка по имени
//    public Group sortName() {
//        Group res = new Group(this.engines);
//        Collections.sort(res.engines, (x, y) -> x.getName().compareToIgnoreCase(y.getName()));
//        return res;
//    }
//
//    // сортировка по мощности
//    public Group sortPower() {
//        List<Engine> qwe = new ArrayList<>(engines);
//        Collections.sort(qwe, (Engine x, Engine y) -> ((Integer) x.getPower()) == ((Integer) y.getPower()) ? 0 :
//                ((Integer) x.getPower()) > ((Integer) y.getPower()) ? 1 : -1);
//        Group res = new Group(qwe);
//        return res;
//    }
//
//    // сортировка по крутящему моменту
//    public Group sortTorque() {
//        List<Engine> qwe = new ArrayList<>(engines);
//        Collections.sort(qwe, (Engine x, Engine y) -> ((Integer) x.getTorque()) == ((Integer) y.getTorque()) ? 0 :
//                ((Integer) x.getTorque()) > ((Integer) y.getTorque()) ? 1 : -1);
//        Group res = new Group(qwe);
//        return res;
//    }


//    public static void takeEngines(List<Engine> engines) {
//        for (DieselEngine item : pickEngineByClass(DieselEngine.class, engines)) {
//            System.out.println("Name:   " + item.getName());
//            System.out.println("Power:  " + item.getPower());
//            System.out.println("Torque: " + item.getTorque());
//            System.out.println("Fuel quality:" + item.getFuel_quality());
//
//        }
//        for (JetTutboEngine item : pickEngineByClass(JetTutboEngine.class, engines)) {
//            System.out.println("Name:   " + item.getName());
//            System.out.println("Power:  " + item.getPower());
//            System.out.println("Torque: " + item.getTorque());
//            System.out.println("Fuel density:" + item.getFuel_density());
//        }
//        for (CombustionEngine item : pickEngineByClass(CombustionEngine.class, engines)) {
//            System.out.println("Name:   " + item.getName());
//            System.out.println("Power:  " + item.getPower());
//            System.out.println("Torque: " + item.getTorque());
//            System.out.println("Additivec:" + item.getAdditivies());
//        }
//    }
}
