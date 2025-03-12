package org.example.electonicdevicesjavafx.service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.electonicdevicesjavafx.model.Device;

public class DeviceServiceImpl implements DeviceService {

    private ObservableList<Device> devices = FXCollections.observableArrayList();

    @Override
    public ObservableList<Device> getDevices() {
        return devices;
    }

    @Override
    public void addDevice(Device device) {
        devices.add(device);
    }

    @Override
    public void removeDevice(Device device) {
        devices.remove(device);
    }
}