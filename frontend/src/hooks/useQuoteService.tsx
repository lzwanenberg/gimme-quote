import { useContext } from 'react';
import { QuoteServiceContext } from '../components/providers/QuoteServiceProvider';
import QuoteService from '@/services/QuoteService/types/QuoteService';

export const useQuoteService = (): QuoteService => {
    const quoteService = useContext(QuoteServiceContext);

    if (!quoteService)
        throw new Error('useQuoteService must be used within a QuoteServiceProvider');

    return quoteService;
};
