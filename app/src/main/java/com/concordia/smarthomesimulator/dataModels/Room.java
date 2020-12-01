package com.concordia.smarthomesimulator.dataModels;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.concordia.smarthomesimulator.enums.HAVCStatus;
import com.concordia.smarthomesimulator.interfaces.IDevice;
import com.concordia.smarthomesimulator.interfaces.IInhabitant;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;

public class Room extends Observable implements Serializable {

    private String name;
    private Geometry geometry;
    private double actualTemperature;
    private double desiredTemperature;
    private HAVCStatus havcStatus;
    private final ArrayList<IInhabitant> inhabitants;
    private final ArrayList<Door> doors;
    private final ArrayList<Light> lights;
    private final ArrayList<Window> windows;

    /**
     * Instantiates a new Room.
     *
     * @param name     the name
     * @param geometry the geometry
     */
    public Room (String name, Geometry geometry) {
        super();

        this.name = name;
        this.geometry = geometry;
        // setting both temps to -999, the timed helper will change it (don't know if that's a good idea)
        this.actualTemperature = 999;
        this.desiredTemperature = 999;
        this.havcStatus = HAVCStatus.OFF;
        this.inhabitants = new ArrayList<>();
        this.windows = new ArrayList<>();
        this.doors = new ArrayList<>();
        this.lights = new ArrayList<>();
    }

    @Override
    public void notifyObservers() {
        for (Light light : lights) {
            if (light.isAutoOn()) {
                light.setIsOpened(inhabitants.size() > 0);
            }
        }
        super.notifyObservers();
    }

    @NonNull
    @Override
    protected Object clone() {
        Room newRoom = new Room(name, geometry);
        ArrayList<IDevice> newDevices = new ArrayList<>();
        for (IDevice device : getDevices()) {
            newDevices.add(device.clone());
        }
        ArrayList<IInhabitant> newInhabitants = new ArrayList<>();
        for (IInhabitant inhabitant : inhabitants) {
            newInhabitants.add(inhabitant.clone());
        }
        newRoom.addDevices(newDevices);
        newRoom.addInhabitants(newInhabitants);
        return newRoom;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == null || obj.getClass() != Room.class)
            return false;

        return name.equals(((Room) obj).getName());
    }

    public HAVCStatus getHavcStatus() {
        return havcStatus;
    }

    public void setHavcStatus(HAVCStatus havcStatus) {
        this.havcStatus = havcStatus;
    }

    public double getDesiredTemperature() {
        return desiredTemperature;
    }

    public void setDesiredTemperature(double desiredTemperature) {
        this.desiredTemperature = desiredTemperature;
    }

    /**
     * Gets temperature.
     *
     * @return the temperature
     */
    public double getActualTemperature() {
        return actualTemperature;
    }

    /**
     * Sets temperature.
     *
     * @param actualTemperature the temperature
     */
    public void setActualTemperature(double actualTemperature) {
        this.actualTemperature = actualTemperature;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets geometry.
     *
     * @return the geometry
     */
    public Geometry getGeometry() {
        return geometry;
    }

    /**
     * Gets inhabitants.
     *
     * @return the inhabitants
     */
    public ArrayList<IInhabitant> getInhabitants() {
        return inhabitants;
    }

    /**
     * Gets devices.
     *
     * @return the devices
     */
    public ArrayList<IDevice> getDevices() {
        ArrayList<IDevice> devices = new ArrayList<>();
        devices.addAll(doors);
        devices.addAll(lights);
        devices.addAll(windows);
        return devices;
    }

    /**
     * Whether an inhabitant is in this room or not.
     *
     * @param name the name of the inhabitant
     * @return whether the inhabitant is in this room or not.
     */
    public boolean hasInhabitant(String name) {
        return this.inhabitants.stream().anyMatch(inhabitant -> inhabitant.getName().equalsIgnoreCase(name));
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets geometry.
     *
     * @param geometry the geometry
     */
    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    /**
     * Add an inhabitant.
     *
     * @param inhabitant the inhabitant
     */
    public void addInhabitant(IInhabitant inhabitant) {
        inhabitants.add(inhabitant);
        notifyObservers();
    }

    /**
     * Add inhabitants.
     *
     * @param inhabitants the inhabitants
     */
    public void addInhabitants(ArrayList<IInhabitant> inhabitants) {
        for (IInhabitant inhabitant : inhabitants) {
            addInhabitant(inhabitant);
        }
    }

    /**
     * Add a device.
     *
     * @param device the device
     */
    public void addDevice(IDevice device) {
        switch (device.getDeviceType()) {
            case DOOR:
                doors.add((Door) device);
                break;
            case LIGHT:
                lights.add((Light) device);
                break;
            case WINDOW:
                windows.add((Window) device);
                break;
        }
    }

    /**
     * Add devices.
     *
     * @param devices the devices
     */
    public void addDevices(ArrayList<IDevice> devices) {
        for (IDevice device : devices) {
            addDevice(device);
        }
    }

    /**
     * Remove an inhabitant.
     *
     * @param name the name
     */
    public void removeInhabitant(String name) {
        for(IInhabitant inhabitant : inhabitants) {
            if (inhabitant.getName().equals(name)) {
                inhabitants.remove(inhabitant);
                break;
            }
        }
        notifyObservers();
    }

    /**
     * Remove a device.
     *
     * @param device the device
     */
    public void removeDevice(IDevice device) {
        switch (device.getDeviceType()) {
            case DOOR:
                doors.remove(device);
                break;
            case LIGHT:
                lights.remove(device);
                break;
            case WINDOW:
                windows.remove(device);
                break;
        }
    }
}
