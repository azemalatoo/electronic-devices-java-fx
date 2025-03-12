package org.example.electonicdevicesjavafx.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import org.example.electonicdevicesjavafx.model.Device;
import org.example.electonicdevicesjavafx.model.Laptop;
import org.example.electonicdevicesjavafx.model.Smartphone;
import org.example.electonicdevicesjavafx.model.Tablet;
import org.example.electonicdevicesjavafx.service.DeviceService;
import org.example.electonicdevicesjavafx.service.DeviceServiceImpl;

public class DeviceManagementController {

    @FXML
    private TabPane tabPane;

    @FXML
    private ListView<Device> listView;

    @FXML
    private ListView<Device> allDevicesListView;

    @FXML
    private ListView<Device> addedDevicesDisplayListView;

    @FXML
    private CheckBox hasStylusCheckBox;

    private DeviceService deviceService = new DeviceServiceImpl();
    private ObservableList<Device> addedDevices = FXCollections.observableArrayList();

    public void initialize() {
        listView.setItems(deviceService.getDevices());
        allDevicesListView.setItems(deviceService.getDevices());
        addedDevicesDisplayListView.setItems(addedDevices);
    }

    @FXML
    protected void onAddButtonClicked() {
        Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
        GridPane grid = (GridPane) selectedTab.getContent();
        TextField nameField = (TextField) grid.getChildren().get(1);
        TextField priceField = (TextField) grid.getChildren().get(3);
        TextField weightField = (TextField) grid.getChildren().get(5);

        if (nameField.getText().isEmpty() || priceField.getText().isEmpty() || weightField.getText().isEmpty()) {
            showAlert("Please fill all fields.", Alert.AlertType.ERROR);
            if (nameField.getText().isEmpty()) {
                nameField.setStyle("-fx-border-color: red;");
            } else {
                nameField.setStyle("");
            }
            if (priceField.getText().isEmpty()) {
                priceField.setStyle("-fx-border-color: red;");
            } else {
                priceField.setStyle("");
            }
            if (weightField.getText().isEmpty()) {
                weightField.setStyle("-fx-border-color: red;");
            } else {
                weightField.setStyle("");
            }
            return;
        }

        try {
            String name = nameField.getText();
            double price = Double.parseDouble(priceField.getText());
            double weight = Double.parseDouble(weightField.getText());

            Device device = null;

            if (selectedTab.getText().equals("Smartphone")) {
                TextField screenSizeField = (TextField) grid.getChildren().get(7);
                TextField cameraResolutionField = (TextField) grid.getChildren().get(9);
                double screenSize = Double.parseDouble(screenSizeField.getText());
                double cameraResolution = Double.parseDouble(cameraResolutionField.getText());
                device = new Smartphone(name, price, weight, screenSize, cameraResolution);
                clearFields(nameField, priceField, weightField, screenSizeField, cameraResolutionField);

            } else if (selectedTab.getText().equals("Laptop")) {
                TextField ramSizeField = (TextField) grid.getChildren().get(7);
                TextField processorTypeField = (TextField) grid.getChildren().get(9);
                int ramSize = Integer.parseInt(ramSizeField.getText());
                String processorType = processorTypeField.getText();
                device = new Laptop(name, price, weight, ramSize, processorType);
                clearFields(nameField, priceField, weightField, ramSizeField, processorTypeField);

            } else if (selectedTab.getText().equals("Tablet")) {
                TextField batteryLifeField = (TextField) grid.getChildren().get(7);
                double batteryLife = Double.parseDouble(batteryLifeField.getText());
                boolean hasStylus = hasStylusCheckBox.isSelected();
                device = new Tablet(name, price, weight, batteryLife, hasStylus);
                clearFields(nameField, priceField, weightField, batteryLifeField);
                hasStylusCheckBox.setSelected(false);
            }

            if (device != null) {
                deviceService.addDevice(device);
                addedDevices.add(device);
                showAlert("Device added!", Alert.AlertType.INFORMATION);
                nameField.setStyle("");
                priceField.setStyle("");
                weightField.setStyle("");
            }

        } catch (NumberFormatException ex) {
            showAlert("Invalid input. Please enter valid numbers.", Alert.AlertType.ERROR);
            clearFields(nameField, priceField, weightField);
            nameField.setStyle("-fx-border-color: red;");
            priceField.setStyle("-fx-border-color: red;");
            weightField.setStyle("-fx-border-color: red;");
        }
    }

    @FXML
    protected void onRemoveButtonClicked() {
        Device selectedItem = listView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            deviceService.removeDevice(selectedItem);
            addedDevices.remove(selectedItem);
        }
    }

    private void clearFields(TextField... fields) {
        for (TextField field : fields) {
            field.clear();
        }
    }

    private void showAlert(String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(alertType.toString());
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }

    public ObservableList<Device> getAddedDevices() {
        return addedDevices;
    }
}