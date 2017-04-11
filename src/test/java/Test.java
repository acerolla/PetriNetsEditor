import java.util.Arrays;
import java.util.List;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTreeCell;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;

public class Test extends Application
{
    List<Employee> employees = Arrays.<Employee>asList(
            new Employee("New Chassi", "New Datacenter"),
            new Employee("New Battery", "New Rack"),
            new Employee("New Chassi", "New Server"),
            new Employee("Anna Black", "Sales Department"),
            new Employee("Rodger York", "Sales Department"),
            new Employee("Susan Collins", "Sales Department"),
            new Employee("Mike Graham", "IT Support"),
            new Employee("Judy Mayer", "IT Support"),
            new Employee("Gregory Smith", "IT Support"),
            new Employee("Jacob Smith", "Accounts Department"),
            new Employee("Isabella Johnson", "Accounts Department"));
    TreeItem<String> rootNode = new TreeItem<String>("MyCompany Human Resources");

    public static void main(String[] args)
    {
        Application.launch(args);
    }

    TreeView<String> treeView = new TreeView<String>(rootNode);

    @Override
    public void start(Stage stage)
    {

        // instantiate the root context menu
        final ContextMenu rootContextMenu
                = ContextMenuBuilder.create()
                .items(
                        MenuItemBuilder.create()
                                .text("Menu Item")
                                .onAction(
                                        new EventHandler<ActionEvent>()
                                        {

                                            public void handle(ActionEvent arg0)
                                            {
                                                System.out.println("Menu Item Clicked!");
                                                System.out.println(arg0.getSource());
                                                System.out.println(arg0.getTarget());

                                            }
                                        }
                                )
                                .build()
                )
                .build();

        treeView.setContextMenu(rootContextMenu);

        rootNode.setExpanded(true);
        for (Employee employee : employees)
        {
            TreeItem<String> empLeaf = new TreeItem<String>(employee.getName());
            boolean found = false;
            for (TreeItem<String> depNode : rootNode.getChildren())
            {
                if (depNode.getValue().contentEquals(employee.getDepartment()))
                {
                    depNode.getChildren().add(empLeaf);
                    found = true;
                    break;
                }
            }
            if (!found)
            {
                TreeItem<String> depNode = new TreeItem<String>(
                        employee.getDepartment()//,new ImageView(depIcon)   // Set picture
                );
                rootNode.getChildren().add(depNode);
                depNode.getChildren().add(empLeaf);
            }
        }

        stage.setTitle("Tree View Sample");
        VBox box = new VBox();
        final Scene scene = new Scene(box, 400, 300);
        scene.setFill(Color.LIGHTGRAY);


        box.getChildren().add(treeView);
        stage.setScene(scene);
        stage.show();
    }

    public static class Employee
    {

        private final SimpleStringProperty name;
        private final SimpleStringProperty department;

        private Employee(String name, String department)
        {
            this.name = new SimpleStringProperty(name);
            this.department = new SimpleStringProperty(department);
        }

        public String getName()
        {
            return name.get();
        }

        public void setName(String fName)
        {
            name.set(fName);
        }

        public String getDepartment()
        {
            return department.get();
        }

        public void setDepartment(String fName)
        {
            department.set(fName);
        }
    }


}