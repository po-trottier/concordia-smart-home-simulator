package com.concordia.smarthomesimulator.adapters;

import androidx.annotation.NonNull;
import com.concordia.smarthomesimulator.dataModels.User;
import com.concordia.smarthomesimulator.interfaces.IInhabitant;

import java.io.Serializable;

public class InhabitantAdapter implements IInhabitant, Serializable {

    private final User user;

    public InhabitantAdapter(User user) {
        this.user = user;
    }

    @Override
    public String getName() {
        return user.getUsername();
    }

    @Override
    public boolean isIntruder() {
        return false;
    }

    @NonNull
    @Override
    public IInhabitant clone() {
        return new InhabitantAdapter(this.user);
    }
}
