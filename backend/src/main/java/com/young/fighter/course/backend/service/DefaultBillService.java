package com.young.fighter.course.backend.service;

import com.young.fighter.course.backend.db.repository.BillRepository;
import com.young.fighter.course.backend.dto.BillView;
import com.young.fighter.course.backend.mapper.BillMapper;
import com.young.fighter.course.backend.service.api.BillService;

import java.util.List;
import java.util.stream.Collectors;

public class DefaultBillService implements BillService {
    private BillRepository billRepository;
    private BillMapper mapper = new BillMapper();

    public DefaultBillService(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    @Override
    public BillView save(BillView view) {
        if (view.getBillId() != null) {
            if (billRepository.findById(view.getBillId()) != null) {
                return mapper.map(billRepository.save(mapper.map(view)));
            } else {
                System.out.println("No such entity");
            }
        }
        return mapper.map(billRepository.save(mapper.map(view)));
    }

    @Override
    public void delete(Long id) {
        if (billRepository.findById(id) != null) {
            billRepository.delete(id);
        } else {
            System.out.println("No such entity");
        }
    }

    @Override
    public BillView findById(Long id) {
        if (billRepository.findById(id) != null) {
            return mapper.map(billRepository.findById(id));
        } else {
            System.out.println("No such entity");
        }
        return null;
    }

    @Override
    public List<BillView> findAll() {
        return billRepository.findAll().stream()
                .map(entity -> mapper.map(entity))
                .collect(Collectors.toList());
    }
}
