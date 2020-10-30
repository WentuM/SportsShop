package services;

import dao.ManufacturerDao;
import model.Manufacturer;

public class ManufacturerServiceImpl implements ManufacturerService {
    private final ManufacturerDao manufacturerDao;

    public ManufacturerServiceImpl(ManufacturerDao manufacturerDao) {
        this.manufacturerDao = manufacturerDao;
    }

    @Override
    public Manufacturer findManufactById(int id) {
        return manufacturerDao.getById(id);
    }
}
