package com.young.fighter.course.backend.service;

import com.young.fighter.course.backend.db.repository.BillRepository;
import com.young.fighter.course.backend.dto.BasketView;
import com.young.fighter.course.backend.dto.BillView;
import com.young.fighter.course.backend.exception.BusinessLogicException;
import com.young.fighter.course.backend.service.api.BillService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DefaultBillService implements BillService {
    private BillRepository billRepository;
    private ModelMapper mapper;

    @Autowired
    public DefaultBillService(BillRepository billRepository, ModelMapper modelMapper) {
        this.billRepository = billRepository;
        this.mapper = modelMapper;
    }

    @Override
    public BillView sale(BasketView view) {
//        if (view.getBillId() != null) {
//            if (billRepository.findById(view.getBillId()).isPresent()) {
//                BillView billView = mapper.map(billRepository.save(mapper.map(view, Bill.class)), BillView.class);
//                log.info("Creating new bill: {}", billView.toString());
//                return billView;
//            } else {
//                log.error("Cannot find product with id: {}", view.getBillId());
//                throw new BusinessLogicException("entity.not.exist");
//            }
//        }
//        log.info("Updating bill: {}", view.toString());
//        return mapper.map(billRepository.save(mapper.map(view, Bill.class)), BillView.class);
        return null;
    }

    @Override
    public void delete(Long id) {
        if (billRepository.findById(id).isPresent()) {
            log.info("Deleting bill with id: {}", id);
            billRepository.deleteById(id);
        } else {
            log.error("Can not delete product with id: {}", id);
            throw new BusinessLogicException("entity.not.exist");
        }
    }

    @Override
    public BillView findById(Long id) {
        if (billRepository.findById(id).isPresent()) {
            return mapper.map(billRepository.findById(id), BillView.class);
        } else {
            log.error("Can not find bill with id: {}", id);
            throw new BusinessLogicException("entity.not.exist");
        }
    }

    @Override
    public List<BillView> findAll() {
        return billRepository.findAll().stream()
                .map(entity -> mapper.map(entity, BillView.class))
                .collect(Collectors.toList());
    }
}
