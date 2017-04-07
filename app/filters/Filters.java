package filters;

import play.http.DefaultHttpFilters;

import javax.inject.Inject;

import play.filters.cors.CORSFilter;

public class Filters extends DefaultHttpFilters {
	@Inject
	public Filters(CORSFilter corsFilter){
		super(corsFilter);
	}
}
