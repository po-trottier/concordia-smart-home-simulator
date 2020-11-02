package com.concordia.smarthomesimulator;

import com.concordia.smarthomesimulator.dataModels.Geometry;
import com.concordia.smarthomesimulator.dataModels.HouseLayout;
import com.concordia.smarthomesimulator.dataModels.Inhabitant;
import com.concordia.smarthomesimulator.dataModels.Room;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class HouseLayoutUnitTest {

    final static float EPSILON = Float.intBitsToFloat(Float.floatToIntBits(1f) + 1);

    @Test
    public void houseLayoutCanBeCreated() {
        // Setup
        HouseLayout layout = new HouseLayout("layout", "image", 1f, 1f, null);
        // Test
        assertNotNull(layout);
    }

    @Test
    public void houseLayoutReturnsProperName() {
        // Setup
        final String name = "layout";
        HouseLayout layout = new HouseLayout(name, "image", 1f, 1f, null);
        // Test
        assertEquals(layout.getName(), name);
    }

    @Test
    public void houseLayoutReturnsProperImage() {
        // Setup
        final String image = "image";
        HouseLayout layout = new HouseLayout("layout", image, 1f, 1f, null);
        // Test
        assertEquals(layout.getImage(), image);
    }

    @Test
    public void houseLayoutReturnsProperGeometry() {
        // Setup
        float width = 1f;
        float height = 2f;
        HouseLayout layout = new HouseLayout("layout", "image", width, height, null);
        // Test
        assertEquals(layout.getGeometry().getWidth(), width, EPSILON);
        assertEquals(layout.getGeometry().getHeight(), height, EPSILON);
        assertNotEquals(layout.getGeometry().getWidth(), height);
        assertNotEquals(layout.getGeometry().getHeight(), width);
    }

    @Test
    public void houseLayoutHasNoRoomByDefault() {
        // Setup
        HouseLayout layout = new HouseLayout("layout", "image", 1f, 1f, null);
        // Test
        assertEquals(layout.getRooms().size(), 0);
    }

    @Test
    public void houseLayoutRoomCanBeAdded() {
        // Setup
        HouseLayout layout = new HouseLayout("layout", "image", 1f, 1f, null);
        // Act
        layout.addRoom(new Room("room", new Geometry()));
        // Test
        assertEquals(layout.getRooms().size(), 1);
    }

    @Test
    public void houseLayoutRoomsCanBeAdded() {
        // Setup
        Room room = new Room("room", new Geometry());
        Room room2 = new Room("room2", new Geometry());
        HouseLayout layout = new HouseLayout("layout", "image", 1f, 1f, null);
        // Act
        layout.addRooms(new ArrayList<>(Arrays.asList(room, room2)));
        // Test
        assertEquals(layout.getRooms().size(), 2);
    }

    @Test
    public void houseLayoutRoomCanBeRemoved() {
        // Setup
        Room room = new Room("room", new Geometry());
        Room room2 = new Room("room2", new Geometry());
        HouseLayout layout = new HouseLayout("layout", "image", 1f, 1f, null);
        layout.addRooms(new ArrayList<>(Arrays.asList(room, room2)));
        // Act
        layout.removeRoom(room.getName());
        // Test
        assertEquals(layout.getRooms().size(), 1);
    }
}