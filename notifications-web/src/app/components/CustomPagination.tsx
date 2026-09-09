import {
  Pagination, PaginationContent, PaginationItem, PaginationLink, PaginationNext, PaginationPrevious
} from "@/components/ui/pagination";
import {
  Select, SelectContent, SelectGroup, SelectItem, SelectTrigger, SelectValue,
} from "@/components/ui/select";
import { Constants } from "@utils/constants";
import { useSearchParams } from "react-router";

interface Props {
  totalPages: number;
  totalElements: number;
}

export function CustomPagination({ totalPages, totalElements }: Props) {
  const [ searchParams, setSearchParams ] = useSearchParams();
  const pageParam = searchParams.get(Constants.PAGE_PARAM_NAME) || Constants.PAGE_DEFAULT;
  const pageSizeParam = searchParams.get(Constants.SIZE_PARAM_NAME) || Constants.SIZE_DEFAULT;

  const handlePageSizeChange = (newSize: string | null) => {
    if (!newSize || newSize === pageSizeParam) return;
    searchParams.set(Constants.SIZE_PARAM_NAME, newSize);
    setSearchParams(searchParams);
    handlePageChange(Constants.PAGE_DEFAULT);
  };

  const handlePageChange = (newPage: string | null) => {
    if (!newPage || newPage === pageParam) return;
    searchParams.set(Constants.PAGE_PARAM_NAME, newPage);
    setSearchParams(searchParams);
  }

  const previousPage = () => {
    handlePageChange((parseInt(pageParam) - 1).toString());
  }

  const nextPage = () => {
    handlePageChange((parseInt(pageParam) + 1).toString());
  }

  return (
    <div className="flex items-center justify-center gap-2 mt-3">
      {/* Page Size Selector */}
      <Select defaultValue={ Constants.SIZE_DEFAULT } onValueChange={ (value) => handlePageSizeChange(value) }>
        <SelectTrigger id="page-size-select">
          <SelectValue />
        </SelectTrigger>
        <SelectContent align="start">
          <SelectGroup>
            {Constants.PAGE_SIZE_OPTIONS.map((option) => (
              <SelectItem key={ option.key } value={ option.value.toString() }>
                { option.value }
              </SelectItem>
            ))}
          </SelectGroup>
        </SelectContent>
      </Select>
      {/* Pagination Controls */}
      <Pagination>
        <PaginationContent>
          <PaginationItem key="previousButton">
            <PaginationPrevious onClick={ previousPage } disabled={ pageParam === "1" }  />
          </PaginationItem>
          
          {getPaginationButtonPages(totalPages, pageParam).map((number) => (
            <PaginationItem key={ number }>
              <PaginationLink isActive={ isActive(number, pageParam) } onClick={ () => handlePageChange(number.toString()) } >
                  { number }
              </PaginationLink>
            </PaginationItem>
          ))}

          <PaginationItem key="nextButton">
            <PaginationNext onClick={ nextPage } disabled={ pageParam === totalPages.toString() } />
          </PaginationItem>
        </PaginationContent>
      </Pagination>
      <div className="text-sm text-muted-foreground w-25 text-center">
        { totalElements } { totalElements === 1 ? "item" : "items" }
      </div>
    </div>
  )
}

const isActive = (pageNumber: number, currentPage: string) => {
  return pageNumber.toString() === currentPage;
}

const getPaginationButtonPages = (totalPages: number, currentPage: string) => {
  if (totalPages <= 0) {
    return [1];
  }
  const arrayIndex = Array.from({ length: totalPages }, (_, i) => i + 1);
  if (totalPages < Constants.LIMIT_PAGINATION_PAGES) {
    return arrayIndex;
  }

  const currentPageNumber = parseInt(currentPage);
  const middleIndex = Math.floor(Constants.LIMIT_PAGINATION_PAGES / 2);

  let startPage = Math.max(1, currentPageNumber - middleIndex);
  let endPage = Math.min(totalPages, currentPageNumber + middleIndex);

  const startPageRes = currentPageNumber - middleIndex - 1;
  const endPageRes = totalPages - (currentPageNumber + middleIndex);

  if (startPageRes < 0) {
    endPage += Math.abs(startPageRes);
  }
  if (endPageRes < 0) {
    startPage -= Math.abs(endPageRes);
  }

  return arrayIndex.slice(startPage - 1, endPage);
}
