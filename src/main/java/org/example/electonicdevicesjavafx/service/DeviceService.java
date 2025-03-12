package org.example.electonicdevicesjavafx.service;

import org.example.electonicdevicesjavafx.model.Device;
import javafx.collections.ObservableList;

public interface DeviceService {
    void addDevice(Device device);
    void removeDevice(Device device);
    ObservableList<Device> getDevices();
}