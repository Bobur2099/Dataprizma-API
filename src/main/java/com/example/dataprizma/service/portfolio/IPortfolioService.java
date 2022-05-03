package com.example.dataprizma.service.portfolio;

import com.example.dataprizma.model.Pagination;
import com.example.dataprizma.model.portfolio.Portfolio;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface IPortfolioService {
    Map<String, Object> findPaginated(Pagination<Portfolio> pagination);

}
