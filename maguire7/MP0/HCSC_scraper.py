"""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""
								READ ME

NOTE: 

THE FOLLOWING MODULES MUST BE INSTALLED

Requests
BeautifulSoup
xlwt
lxml


To install on LINUX system, run the following commands:

> sudo apt-get install python-pip
> sudo pip install beautifulsoup4
> sudo pip install requests
> sudo pip install xlwt
> sudo pip install lxml



TO RUN:
from within the same directory that this file is stored in run the command and follow the onscreen instructions

	> python HCSC_scraper.py

and follow on screen instructions

"""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""



import re
import xlwt
import sys
import requests
from bs4 import BeautifulSoup
from time import time

BASE_URL = 'https://public.hcsc.net/providerfinder/home.do'
BASE_SEARCH_URL = 'https://public.hcsc.net/providerfinder/search.do?pageId=476792594&actionType=Search&zipcode=&gender=&county=&city=&hospital=&language=&handicap=&custGrp=&alpha=&suppNtwk=&residenceSt=&product=&memberid=&tierId=&'\
'resState=%s&'\
'state=%s&networkDisplayID=&planID=&alphaprefix=&providerTypeID=&specialtyID=&limitationID=&name=&location=&radius='
SEARCH_URL = 'https://public.hcsc.net/providerfinder/search-results.do?pageId=476792594&actionType=Page&'\
'networkID=%s&networkDisplayID=&subNetwork=&subNetworkID=&pod=&podID=&ecpCategory=&ecp=&ecpCategoryCd=&ecpCd=&'\
'providerTypeID=%s&'\
'specialtyID=%s&providerID=&'\
'newPageNumber=%s&' \
'currentPageNumber=%s&numberOfPages=&crumbIndex=0&inLat=&maxLat=&minLng=&maxLng=&centerLat=&centerLng=&'\
'zipcode=%s&gender=&providerType=&network=&plan=&plannID=&alphaprefix=&'\
'alphafilter=%s&specialty=&language=&radius=&name=&location=&acceptingNewPatients=&spanishProviders=&handicap=&recognizedOnly=&'\
'state=%s&'\
'resState=%s&city=&hospital=&'\
'county=%s&extendedHours=&sort=&limitationID=&limitation=&boardCert=&totalResults=&custGrp=&custGrpName=&memberid=&tierId=&bridgesToExcel=&th=&totalResults=&emailPdfTxt=&viewPdf'

REFINE_URL = 'https://public.hcsc.net/providerfinder/search-results.do?actionType=GetRefinedDropdownInfo&pageId=476792594&'\
'state=%s&'\
'networkID=%s&plan=&'\
'specialty=%s&'\
'providerType=%s&custGrp=undefined&alpha=undefined&corpEntCd=undefined'
ZIPS_URL = 'https://public.hcsc.net/providerfinder/search-results.do?pageId=476792594&actionType=DrillDownZipCodes&count=1'
COUNTIES_URL =    'https://public.hcsc.net/providerfinder/search-results.do?pageId=476792594&actionType=DrillDownCounties&count=1'
SPECIALTIES_URL = 'https://public.hcsc.net/providerfinder/search-results.do?pageId=476792594&actionType=DrillDownSpecialties&count=50'
ALPHABET = [chr(i).upper() for i in range(ord('a'),ord('a')+26)]

CLOCK = 0
TIMER = 0
state_abbr = ''
book = 	xlwt.Workbook(encoding='utf-8')
cur_sheet = None
row_num = 1
reset_count = 0
written_count = 0


def create_new_sheet(state):
	global cur_sheet, book, row_num, reset_count
	
	row_num = 1
	reset_count += 1
	cur_sheet = book.add_sheet('%s_%s' % (state, str(reset_count)), cell_overwrite_ok=True)
	cur_sheet.write(0, 0, 'Provider Name')
	cur_sheet.write(0, 1, 'Provider Organization')	
	cur_sheet.write(0, 2, 'Network')
	cur_sheet.write(0, 3, 'Provider Type')
	cur_sheet.write(0, 4, 'Specialty')
	cur_sheet.write(0, 5, 'State')
	cur_sheet.write(0, 6, 'County')
	cur_sheet.write(0, 7, 'City')
	cur_sheet.write(0, 8, 'Zipcode')
	cur_sheet.write(0, 9, 'Provider Expertise')
	cur_sheet.write(0, 10, 'Provider is in Provider Organization')

def get_network_types(state, networkID='', providerTypeID='', specialtyID=''):
	session_text = session.get(REFINE_URL % (state, networkID, specialtyID, providerTypeID)).text #REFINE_URL % (state, networkID, specialtyID, providerTypeID)
	types_text = re.findall('\"networks\":\[(.+?)\]', session_text)
	options = re.findall('\"name\":\"(.+?)\",\"id\":\"(.*?) *\"', str(types_text))
	types = dict()
	for option in options:
		code_val = option[1]
		name_val = option[0]
		if code_val != '':
			types[code_val] = name_val
	return types

def get_provider_types(state, networkID, providerTypeID='', specialtyID=''):
	session_text = session.get(REFINE_URL % (state, networkID, specialtyID, providerTypeID)).text
	types_text = re.findall('\"types\":\[(.+?)\]', session_text)
	options = re.findall('\"name\":\"(.+?)\",\"id\":\"(.*?) *\"', str(types_text))
	types = dict()
	for option in options:
		code_val = option[1]
		name_val = option[0]
		if code_val != '':
			types[code_val] = name_val
	return types

def get_specialties(state, networkID, providerTypeID, specialtyID=''):
	session_text = session.get(REFINE_URL % (state, networkID, specialtyID, providerTypeID)).text
	types_text = re.findall('\"specialties\":\[(.+?)\]', session_text)
	options = re.findall('\"name\":\"(.+?)\",\"id\":\"(.*?) *\"', str(types_text))
	types = dict()
	for option in options:
		code_val = option[1]
		name_val = option[0]
		if code_val != '':
			types[code_val] = name_val
	return types

def get_zipcode_list():
	zips_text = session.get(ZIPS_URL).text
	zips_list = re.findall(r'[A-Z]{2} (\d\d\d\d\d)', zips_text)
	zips_list = set(zips_list)
	return zips_list

def get_counties_list():
	county_text = session.get(COUNTIES_URL).text
	county_list =  re.findall('\"name\":\"(.+?)\"', str(county_text))
	types = dict()
	county_list = set(county_list)
	return county_list

def get_num_results(soup):
	num_results_raw = soup.find(attrs={'class':'results_header'})
	if num_results_raw == None:
		return -1
	results = re.findall(r'\(of ([\d,]+)\)', num_results_raw.get_text())
	if len(results) != 0:
		return int(results[0].replace(',',''))
	else:
		return 0

def get_and_write_page_results(soup, cur_sheet, provider_type, network_type, specialty, county):
	global row_num, written_count

	result_rows = soup.find_all(attrs={'class':'result_row'})
	if len(result_rows) == 0:
		return -1
	count = 0
	for row in result_rows:
		count += 1
		searchable_text = row.td.get_text()
		provider_name = row.em.get_text()
		organization_raw = row.td.contents[4].strip()
		zipcode_search = re.search(r'\n\s*(.*?),\s*?%s\s*?(\d\d\d\d\d)'%state_abbr, searchable_text)
		#print zipcode_search
		if zipcode_search == None:
			continue
		else:
			city = zipcode_search.group(1)
			#print city
			zipcode = zipcode_search.group(2)
		if re.match('Po Box|\d', str(organization_raw)) == None:
			organization = organization_raw
			provider_is_organization = 'NO'
			provider_expertise_search = re.search('([A-Z]{2,5}\Z|PhD\Z|PsyD\Z|AuD\Z)', provider_name)
			if provider_expertise_search != None:
				provider_expertise = provider_expertise_search.group(1)
			else:
				provider_expertise = ''
		else:
			organization = provider_name
			provider_is_organization = 'YES'
			special_case = re.search('([A-Z]{2,5}\Z|PhD\Z|PsyD\Z|AuD\Z)', provider_name)
			if special_case != None:

				provider_expertise = special_case.group(1)
			else:
				provider_expertise = ''
		cur_sheet.write(row_num, 0, provider_name)
		cur_sheet.write(row_num, 1, organization)	
		cur_sheet.write(row_num, 2, network_type)
		cur_sheet.write(row_num, 3, provider_type)
		cur_sheet.write(row_num, 4, specialty)
		cur_sheet.write(row_num, 5, state_abbr)
		cur_sheet.write(row_num, 6, county)
		cur_sheet.write(row_num, 7, city)
		cur_sheet.write(row_num, 8, zipcode)
		cur_sheet.write(row_num, 9, provider_expertise)
		cur_sheet.write(row_num, 10, provider_is_organization)

		row_num += 1
		written_count += 1

		#if row_num > 50000:
			#print 'created new sheet'
			#create_new_sheet(state)
	if count <49:
		return -1
	else:
		return 0

def make_soup(url):
	soup = None
	request_response = None
	while request_response == None:
		try:
			request_response = session.get(url)
			soup = BeautifulSoup(request_response.text, 'lxml')
		except:
			print 'request stalling on \t %s' % url
			continue
	return soup

def print_clock():
	global CLOCK, TIMER
	if CLOCK == 0:
		CLOCK = time()
		return
	TIMER += (time() - CLOCK)
	print TIMER/60
	CLOCK = time()

if __name__ == '__main__':
	
	name_to_save_as = raw_input('Enter file name to save as, excluding .<filetype>  (e.g. HCSC_IL_Data) : ')
	while (1):
	
		resState = state = state_abbr = raw_input('This is the state you wish to get information from\n'\
											  'Enter a State abbreviation (e.g. IL, OK, TX) or \'END\' to exit : ')
		if resState.upper() == 'END':
			exit()
		session = requests.Session()
		session.get(BASE_URL)
		create_new_sheet(state)

		network_types = get_network_types(state)
		network_keys = network_types.keys()
		count1 = 0
		for network_key in network_keys:
			count1 += 1
			print_clock()
			provider_types = get_provider_types(state, network_key)
			provider_type_keys = provider_types.keys()
			count2 = 0
			for provider_type_key in provider_type_keys:
				print_clock()
				count2 += 1
				specialties = get_specialties(state, network_key, provider_type_key)
				specialty_keys = specialties.keys()
				count3 = 0
				for specialty_key in specialty_keys:
					#book.save('%s.xlsx' % (name_to_save_as))
					count3 += 1
					print "\t%s/%s\t\t%s/%s\t\t%s/%s\n" % (str(count1), str(len(network_keys)), str(count2), str(len(provider_type_keys)), str(count3), str(len(specialty_keys)))
					print "network_key: %50s\tprovider_type_key: %50s\tspecialty_key: %s" % (network_types[network_key], provider_types[provider_type_key], specialties[specialty_key])
					nps_search_url = SEARCH_URL % (network_key, provider_type_key, specialty_key, '1', '1','', '', state, resState, '')
					soup = make_soup(nps_search_url)
					counties = get_counties_list()
					count4 = 0
					for county in counties:
						count4 += 1
						print 'county:%30s\t%s/%s' % (county, str(count4), str(len(counties)))
						npsc_search_url = SEARCH_URL % (network_key, provider_type_key, specialty_key, '1', '1','', '', state, resState, county)
						soup = make_soup(npsc_search_url)
						num_results = get_num_results(soup)
						if num_results == -1:
							print 'no search results for\n%s' % npsc_search_url
							break
						if num_results == 0:
							print 'length of soup results was 0\n', npsc_search_url
							break
						if num_results >=750:
							for letter in ALPHABET:
								#if letter != 'E':
									#continue
								for page in range(1,16):
									#make request
									npsl_search_url = SEARCH_URL % (network_key, provider_type_key, specialty_key, str(page), str(page),'', letter, state, resState, county)
									soup = make_soup(npsl_search_url)
									num_results = get_num_results(soup)
									if num_results == -1:
										print 'no search results for letter %s in url: %s' % (letter, npsl_search_url)
										break
									if num_results == 0:
										print 'no search results for letter %s on page %s in url: %s' % (letter, page, npsl_search_url)
										break
									elif num_results >=750:
										zipcodes = get_zipcode_list()
										for zipcode in zipcodes:
											npslz_search_url = SEARCH_URL % (network_key, provider_type_key, specialty_key, str(page), str(page), zipcode, letter, state, resState, county)
											soup = make_soup(npslz_search_url)
											if -1 == get_and_write_page_results(soup, cur_sheet, provider_types[provider_type_key], network_types[network_key], specialties[specialty_key], county):
												break	
									else:
										get_and_write_page_results(soup, cur_sheet, provider_types[provider_type_key], network_types[network_key], specialties[specialty_key], county)
						else:
							for page in range(1,16):
								#make request
								nps_search_url = SEARCH_URL % (network_key, provider_type_key, specialty_key, str(page), str(page),'', '', state, resState, county)
								soup = make_soup(nps_search_url)
								if -1 == get_and_write_page_results(soup, cur_sheet, provider_types[provider_type_key], network_types[network_key], specialties[specialty_key], county):
									break
					#print 'time taken: ' + str(time()-start) + '\n'
				book.save('%s.xlsx' % (name_to_save_as))

	
	
	print_clock()
